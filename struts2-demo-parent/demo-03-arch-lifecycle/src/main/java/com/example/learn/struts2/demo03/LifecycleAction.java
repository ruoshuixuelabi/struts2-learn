package com.example.learn.struts2.demo03;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.action.Action;

public class LifecycleAction extends ActionSupport {

    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("[LOGGER] 进入 Action 业务逻辑...");
        message = "Lifecycle complete: Filter -> ActionProxy -> InterceptorStack -> Action -> Result";
        // 模拟业务耗时
        Thread.sleep(50);
        return Action.SUCCESS;
    }
}
