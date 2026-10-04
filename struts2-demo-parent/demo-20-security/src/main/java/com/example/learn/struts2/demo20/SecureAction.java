package com.example.learn.struts2.demo20;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

public class SecureAction extends ActionSupport {

    // Struts 7.x：@StrutsParameter 仅支持 depth() 属性；required=true 已被移除
    @StrutsParameter
    private String username;

    @StrutsParameter
    private String token;

    private String display;

    public String execute() {
        if (username == null || username.trim().isEmpty()) {
            addFieldError("username", "username 必填");
            return INPUT;
        }
        // 仅做简单拼接；不在 OGNL 表达式中暴露危险字段
        display = "welcome, " + username;
        return SUCCESS;
    }

    // 一个普通业务方法（被允许通过 strictMethodInvocation 调用）
    public String info() {
        display = "info: struts 7.3.0 hard demo";
        return SUCCESS;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getDisplay() { return display; }
    public void setDisplay(String display) { this.display = display; }
}