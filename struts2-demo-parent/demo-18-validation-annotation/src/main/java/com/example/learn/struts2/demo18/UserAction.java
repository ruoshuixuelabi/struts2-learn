package com.example.learn.struts2.demo18;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.validator.annotations.StringLengthFieldValidator;
import org.apache.struts2.validator.annotations.IntRangeFieldValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserAction extends ActionSupport {

    // JSR-303 注解（需 struts2-bean-validation-plugin）
    @NotNull(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在 3-20 之间")
    private String username;

    @NotNull
    @Email(message = "邮箱格式错误")
    private String email;

    @NotNull
    @Min(value = 0, message = "年龄不能小于 0")
    @Max(value = 150, message = "年龄不能大于 150")
    private Integer age;

    private String message;

    public String save() {
        message = "已保存用户：" + username + "（" + email + "，age=" + age + "）";
        return SUCCESS;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}