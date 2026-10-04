package com.example.learn.struts2.demo15;

import org.apache.struts2.ActionSupport;

public class GreetAction extends ActionSupport {

    private String name;
    private String message;

    public String execute() {
        if (name == null || name.trim().isEmpty()) {
            addFieldError("name", "name 不能为空");
            return INPUT;
        }
        message = "Hello, " + name + "! (Struts 7.3.0)";
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}