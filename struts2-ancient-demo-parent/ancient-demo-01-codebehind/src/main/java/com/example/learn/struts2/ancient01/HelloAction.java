package com.example.learn.struts2.ancient01;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
    private String message = "CodeBehind Plugin Demo (Struts 2.3.37)";

    public String execute() {
        return SUCCESS;
    }

    public String getMessage() { return message; }
}