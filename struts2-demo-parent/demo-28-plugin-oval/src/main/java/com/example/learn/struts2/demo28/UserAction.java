package com.example.learn.struts2.demo28;

import org.apache.struts2.ActionSupport;
import net.sf.oval.constraint.NotNull;

public class UserAction extends ActionSupport {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "邮箱不能为空")
    private String email;

    @NotNull(message = "嵌套对象不能为空")
    // OVal 1.90 的 @ValidateWithMethod 已不再支持 parameters 数组，必须用 parameterType；
    // 本 demo 简化为仅做 NotNull 校验，嵌套对象的 @ValidateWithMethod 校验示例见 README。
    private User nestedUser;

    public String save() {
        return SUCCESS;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public User getNestedUser() { return nestedUser; }
    public void setNestedUser(User nestedUser) { this.nestedUser = nestedUser; }
}