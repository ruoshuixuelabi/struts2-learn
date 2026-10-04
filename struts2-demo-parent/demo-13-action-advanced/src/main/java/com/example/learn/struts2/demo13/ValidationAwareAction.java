package com.example.learn.struts2.demo13;

import org.apache.struts2.ActionSupport;

/**
 * 演示 ValidationAware 接口（在 ActionSupport 中已实现）：
 *
 *   - addFieldError(name, msg)  按字段记录错误（JSP 用 <s:fielderror field="name"/> 显示）
 *   - addActionError(msg)       全局错误（<s:actionerror/> 显示）
 *   - fieldErrors / actionErrors 收集到的错误
 *
 * 一旦存在 field/action error，validation 拦截器会让 Result 返回 "input"。
 */
public class ValidationAwareAction extends ActionSupport {

    private String username;
    private Integer age;

    public String check() {
        // 模拟校验
        if (username == null || username.trim().isEmpty()) {
            addFieldError("username", "用户名不能为空");
        }
        if (username != null && username.length() > 20) {
            addFieldError("username", "用户名长度不能超过 20");
        }
        if (age == null) {
            addFieldError("age", "年龄不能为空");
        } else if (age < 0 || age > 150) {
            addFieldError("age", "年龄必须在 0-150 之间");
        }

        if (hasFieldErrors()) {
            return INPUT;   // 失败：跳回 input 页
        }
        return SUCCESS;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}