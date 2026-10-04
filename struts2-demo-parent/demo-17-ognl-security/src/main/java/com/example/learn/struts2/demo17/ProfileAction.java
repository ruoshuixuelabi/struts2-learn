package com.example.learn.struts2.demo17;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

public class ProfileAction extends ActionSupport {

    // 7.x 默认 strictParameterization=true：未加 @StrutsParameter 的字段不会被注入
    @StrutsParameter
    private String username;

    // Struts 7.x 的 @StrutsParameter 仅有 depth() 属性；required 在 OGNL 安全机制里通过
    // strictParameterization=true 配合手动校验实现。
    @StrutsParameter
    private String email;

    private String displayName;

    public String execute() {
        if (username == null || username.trim().isEmpty()) {
            addFieldError("username", "username 不能为空");
            return INPUT;
        }
        displayName = "user-" + username;
        return SUCCESS;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}