package com.example.learn.struts2.demo35;

import org.apache.struts2.ActionSupport;

/**
 * 进度查询 Action（被前端轮询 / 等待页 meta refresh 调用）。
 */
public class ProgressAction extends ActionSupport {

    private String taskId;
    private int progress;
    private boolean complete;
    private String result;

    @Override
    public String execute() {
        TaskStore.TaskStatus s = (taskId != null) ? TaskStore.get(taskId) : null;
        if (s != null) {
            progress = s.getProgress();
            complete = s.isComplete();
            result = s.getResult();
        } else {
            progress = 0;
            complete = false;
            result = null;
        }
        return SUCCESS;
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public int getProgress() { return progress; }
    public boolean isComplete() { return complete; }
    public String getResult() { return result; }
}