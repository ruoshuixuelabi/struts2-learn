package com.example.learn.struts2.legacy01;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
    private User user = new User();

    public String save() {
        return SUCCESS;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}