package com.example.learn.struts2.demo01;

import org.apache.struts2.action.Action;

public class HelloAction implements Action {
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String execute() {
        message = "Hello, Struts 7.3.0!";
        return SUCCESS;
    }
}