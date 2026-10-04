package com.example.learn.struts2.demo35;

/**
 * 抽离长任务业务逻辑，供两种入口复用：
 * <ul>
 *   <li>{@link LongTaskAction} 通过 {@code execAndWait} 拦截器自动在后台线程同步执行（Struts 7.x 拦截器名；旧名 {@code executeAndWait} 已移除）</li>
 *   <li>{@link TaskCreateAction} 显式启线程调用（本类 {@link #run(String)}）</li>
 * </ul>
 * <p>
 * 单独抽出是为了避免双重启动：拦截器模式下若 action.execute() 再启线程，
 * 同一个任务会被两个 worker 并发跑，浪费资源且进度更新错乱。
 */
public final class TaskRunner {

    private TaskRunner() {}

    /**
     * 同步执行模拟长任务：每 200ms 把进度推 10%，约 2 秒完成。
     * 真实业务应替换为报表导出 / 视频转码 / 大批量写入等。
     */
    public static void run(String taskId) {
        TaskStore.TaskStatus s = TaskStore.get(taskId);
        if (s == null) return;
        try {
            for (int i = 10; i <= 100; i += 10) {
                s.setProgress(i);
                Thread.sleep(200);
            }
            s.setResult("导出 2024-Q1 销售报表（共 1287 行）");
            s.setProgress(100);
            s.setComplete(true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}