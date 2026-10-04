package com.example.learn.struts2.demo28;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

public class User {

    @NotNull(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度必须在 3-20 之间")
    private String username;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @MatchPattern(pattern = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String phone;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}