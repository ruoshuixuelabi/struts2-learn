package com.example.learn.struts2.demo35;

import org.apache.struts2.ActionSupport;

/**
 * demo-35: AJAX 入口——创建任务并立即返回 taskId（JSON）。
 * <p>
 * 不走 {@code execAndWait} 拦截器（AJAX 不需要渲染等待 JSP；Struts 7.x 已将
 * 旧名 {@code executeAndWait} 移除，统一用 camelCase），
 * 显式启动后台 worker 跑任务。
 *
 * <p>前端流程：</p>
 * <ol>
 *   <li>{@code fetch('async/task-create.action')} → 得到 {@code {taskId}}</li>
 *   <li>{@code new EventSource('async/progress-sse.action?taskId=...')} 订阅进度</li>
 * </ol>
 */
public class TaskCreateAction extends ActionSupport {

    private String taskId;

    @Override
    public String execute() {
        TaskStore.TaskStatus s = TaskStore.create();
        this.taskId = s.getTaskId();

        Thread worker = new Thread(() -> TaskRunner.run(taskId), "task-" + taskId);
        worker.setDaemon(true);
        worker.start();
        return SUCCESS;
    }

    public String getTaskId() { return taskId; }
}