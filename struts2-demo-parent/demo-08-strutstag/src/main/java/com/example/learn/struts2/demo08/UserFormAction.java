package com.example.learn.struts2.demo08;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFormAction extends ActionSupport {

    private User user = new User();
    private List<String> cities = new ArrayList<>(Arrays.asList("BJ", "SH", "GZ", "SZ"));
    @StrutsParameter(depth = 1)
    public User getUser() {
        return user;
    }
    @StrutsParameter(depth = 1)
    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCities() {
        return cities;
    }

    /** 显示输入表单 */
    public String input() {
        return INPUT;
    }

    /** 提交表单 */
    public String save() {
        System.out.println("[UserFormAction] save user: name=" + user.getName()
                + ", age=" + user.getAge() + ", city=" + user.getCity());
        addActionMessage("保存成功: " + user.getName());
        return SUCCESS;
    }
}
