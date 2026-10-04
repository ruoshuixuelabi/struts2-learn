package com.example.learn.struts2.demo04;

import org.apache.struts2.ActionSupport;

public class HelloAction extends ActionSupport {

    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("HelloAction 业务逻辑...");
        message = "Hello from Demo 04 (basic interceptor)";
        return SUCCESS;
    }
}
