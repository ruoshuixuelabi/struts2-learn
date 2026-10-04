package com.example.learn.struts2.legacy01;

import net.sf.oval.constraints.Email;
import net.sf.oval.constraints.Length;
import net.sf.oval.constraints.NotNull;

public class User {
    @NotNull(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度 3-20")
    private String username;

    @NotNull
    @Email(message = "邮箱格式错误")
    private String email;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}