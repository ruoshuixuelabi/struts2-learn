package com.example.learn.struts2.demo35;

import org.apache.struts2.ActionSupport;

/**
 * demo-35: 基于 {@code execAndWait} 拦截器的长任务 Action。
 *
 * <h2>现代拦截器机制（Struts 2.5+/7.x）</h2>
 * <ul>
 *   <li>Struts 7.x 把拦截器注册名从 {@code executeAndWait} 改名为 {@code execAndWait}（camelCase 一致性）。旧名已移除。</li>
 *   <li>{@code ExecuteAndWaitInterceptor} 自己在后台线程池执行 {@link #execute()}；
 *       HTTP 主线程永不阻塞</li>
 *   <li>第一次请求：拦截器启动后台进程后立即返回 {@code "wait"} → 渲染 wait.jsp</li>
 *   <li>后续 meta refresh 请求：拦截器检查后台是否完成
 *       <ul>
 *         <li>未完成 → 继续返回 {@code "wait"}</li>
 *         <li>完成 → 返回 {@link #execute()} 的返回值（即 {@code bp.getResult()}）</li>
 *       </ul>
 *   </li>
 *   <li>因此 {@link #execute()} 应该<strong>同步执行实际业务</strong>并返回真实结果码（如 {@code SUCCESS}），
 *       <strong>不要</strong>自己再启线程（会导致双重启动）或返回 {@code "wait"}（会让拦截器永远拿到 "wait"）</li>
 * </ul>
 *
 * <h2>关于 ExecuteAndWaitHandler 接口</h2>
 * Struts 2.5+ 拦截器不再调用 action 的 {@code getResult()} / {@code isDone()}，
 * 因此本类不再实现该接口（早期版本需要）。
 *
 * <h2>wait.jsp 配合</h2>
 * 必须 {@code <meta refresh>} 跳回<strong>同一个</strong> long-task.action URL（带原参数），
 * 拦截器才能正确轮询同一个 session 里的 BackgroundProcess。
 */
public class LongTaskAction extends ActionSupport {

    private String taskId;
    private int progress;

    @Override
    public String execute() {
        TaskStore.TaskStatus status = TaskStore.create();
        this.taskId = status.getTaskId();

        // 拦截器会把这个方法放到后台线程跑（不阻塞 HTTP 线程）
        // 我们直接同步执行真实工作，让拦截器拿到 SUCCESS 作为 bp.getResult()
        TaskRunner.run(taskId);

        TaskStore.TaskStatus s = TaskStore.get(taskId);
        this.progress = (s != null) ? s.getProgress() : 0;
        return SUCCESS;
    }

    public String getTaskId() { return taskId; }
    public int getProgress() { return progress; }
}