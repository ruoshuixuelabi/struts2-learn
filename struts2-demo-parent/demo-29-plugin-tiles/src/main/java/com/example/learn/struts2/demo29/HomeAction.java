package com.example.learn.struts2.demo29;

import org.apache.struts2.ActionSupport;

public class HomeAction extends ActionSupport {
    private String message = "Tiles 3 布局演示首页";

    public String execute() {
        return SUCCESS;
    }

    public String getMessage() { return message; }
}