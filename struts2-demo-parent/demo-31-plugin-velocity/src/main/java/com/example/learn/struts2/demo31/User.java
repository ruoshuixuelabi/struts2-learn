package com.example.learn.struts2.demo31;

public class User {
    private String username;
    private String email;
    private boolean vip;

    public User(String username, String email, boolean vip) {
        this.username = username;
        this.email = email;
        this.vip = vip;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public boolean isVip() { return vip; }
}