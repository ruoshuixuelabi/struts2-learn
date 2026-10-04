package com.example.learn.struts2.demo31;

import org.apache.struts2.ActionSupport;

public class EmailPreviewAction extends ActionSupport {

    private User user = new User("alice", "alice@example.com", true);
    private String activationUrl = "https://example.com/activate?token=abc123";

    public String preview() {
        return SUCCESS;
    }

    public User getUser() { return user; }
    public String getActivationUrl() { return activationUrl; }
}