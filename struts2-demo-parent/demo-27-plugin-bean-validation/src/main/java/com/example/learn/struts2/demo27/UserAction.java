package com.example.learn.struts2.demo27;

import org.apache.struts2.ActionSupport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UserAction extends ActionSupport {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "邮箱不能为空")
    private String email;

    @Min(value = 0, message = "年龄不能小于 0")
    @Max(value = 150, message = "年龄不能大于 150")
    private Integer age;

    @NotNull(message = "嵌套对象不能为空")
    @Valid
    private User nestedUser;

    public String save() {
        return SUCCESS;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public User getNestedUser() { return nestedUser; }
    public void setNestedUser(User nestedUser) { this.nestedUser = nestedUser; }
}