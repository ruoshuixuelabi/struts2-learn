package com.example.learn.struts2.demo35;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.struts2.ActionSupport;

import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * demo-35: SSE 实时推送（Servlet 3.0+ AsyncContext + Struts 异步插件）。
 *
 * <h2>关键设计</h2>
 * <ul>
 *   <li>用 {@link ScheduledExecutorService} 而不是 {@code Thread.sleep} 循环，避免占用异步线程</li>
 *   <li>定期写 {@code ":keepalive\n\n"} 心跳，防止 Nginx / 反向代理 60 秒超时断开</li>
 *   <li>{@link AsyncListener} 在 onComplete / onError / onTimeout 清理 ScheduledFuture，防止泄漏</li>
 *   <li>{@code request} / {@code response} 由 Struts 的 servletConfig 拦截器注入</li>
 * </ul>
 */
public class ProgressSSEAction extends ActionSupport {

    /** 全局共享的 SSE 推送调度器（生产环境可注入 Bean） */
    private static final ScheduledExecutorService SCHEDULER =
        Executors.newScheduledThreadPool(4, r -> {
            Thread t = new Thread(r, "sse-pusher");
            t.setDaemon(true);
            return t;
        });

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String taskId;

    @Override
    public String execute() throws Exception {
        // 1. 设置 SSE 响应头（必须先于 startAsync）
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        // 2. 启动异步上下文，释放 Servlet 线程
        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.setTimeout(60_000L);   // 60 秒无活动则超时

        final PrintWriter writer = asyncCtx.getResponse().getWriter();

        // 3. 心跳任务：每 15 秒写注释行（EventSource 会忽略 : 开头的行）
        // 注：PrintWriter.write/flush 不抛 IOException，但客户端断开时 flush 会返回 false
        ScheduledFuture<?> heartbeat = SCHEDULER.scheduleAtFixedRate(() -> {
            writer.write(":keepalive\n\n");
            if (writer.checkError()) {
                asyncCtx.complete();
            }
        }, 15, 15, TimeUnit.SECONDS);

        // 4. 进度推送任务：每 200ms 写一条 progress 事件
        ScheduledFuture<?> pusher = SCHEDULER.scheduleAtFixedRate(() -> {
            TaskStore.TaskStatus s = (taskId != null) ? TaskStore.get(taskId) : null;
            int pct = (s != null) ? s.getProgress() : 0;
            boolean done = s != null && s.isComplete();

            writer.write("event: progress\n");
            writer.write("data: " + pct + "\n\n");

            if (done) {
                String result = s.getResult() == null ? "" : s.getResult();
                writer.write("event: done\n");
                writer.write("data: " + result + "\n\n");
            }

            if (writer.checkError() || done) {
                asyncCtx.complete();   // 触发 AsyncListener.onComplete
            }
        }, 0, 200, TimeUnit.MILLISECONDS);

        // 5. AsyncListener：任何终止路径都取消调度任务
        asyncCtx.addListener(new AsyncListener() {
            @Override public void onComplete(AsyncEvent e) { cancel(heartbeat, pusher); }
            @Override public void onTimeout(AsyncEvent e) { cancel(heartbeat, pusher); asyncCtx.complete(); }
            @Override public void onError(AsyncEvent e) { cancel(heartbeat, pusher); asyncCtx.complete(); }
            @Override public void onStartAsync(AsyncEvent e) { /* no-op */ }
        });

        return NONE;   // 不返回视图，SSE 流直接写到响应
    }

    private static void cancel(ScheduledFuture<?>... futures) {
        for (ScheduledFuture<?> f : futures) {
            if (f != null && !f.isDone()) f.cancel(false);
        }
    }

    // servletConfig 拦截器注入
    public void setServletRequest(HttpServletRequest request) { this.request = request; }
    public void setServletResponse(HttpServletResponse response) { this.response = response; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
}