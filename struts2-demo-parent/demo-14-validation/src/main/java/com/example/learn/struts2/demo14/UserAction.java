package com.example.learn.struts2.demo14;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

/**
 * 演示 XML 校验。
 *
 * 校验规则定义在与本类同包的 UserAction-validation.xml 中：
 *   - username：必填、长度 3-20
 *   - age：整数、范围 0-150
 *
 * 校验失败时 validation 拦截器返回 "input"，struts.xml 中配置该 result 跳回表单。
 */
public class UserAction extends ActionSupport {

    private String username;
    private Integer age;

    public String save() {
        // 校验通过才进入这里
        return SUCCESS;
    }

    public String getUsername() { return username; }
    @StrutsParameter
    public void setUsername(String username) { this.username = username; }
    public Integer getAge() { return age; }
    @StrutsParameter
    public void setAge(Integer age) { this.age = age; }
}