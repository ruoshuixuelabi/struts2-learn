package com.example.learn.struts2.demo25;

import org.apache.struts2.action.Action;

/**
 * 简单 Action：测试注入到 user.name 后能否被读取。
 */
public class HelloAction implements Action {

    private User user;
    private String message;

    @Override
    public String execute() {
        if (user != null && user.getName() != null) {
            message = "Hello, " + user.getName() + "!";
        } else {
            message = "Hello, Struts 7.3.0 (JUnit 5)!";
        }
        return SUCCESS;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public static class User {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
