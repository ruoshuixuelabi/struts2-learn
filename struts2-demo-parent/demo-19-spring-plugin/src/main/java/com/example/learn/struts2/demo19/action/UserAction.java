package com.example.learn.struts2.demo19.action;

import org.apache.struts2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.learn.struts2.demo19.service.UserService;

import java.util.List;

public class UserAction extends ActionSupport {

    @Autowired
    private UserService userService;

    /**
     * Setter 注入（兼容测试模式：在测试环境下不走 Spring，由测试代码手动注入 mock）。
     */
    public void setUserService(UserService userService) { this.userService = userService; }

    private List<String> users;
    private String oneUser;

    public String list() {
        users = userService.findAllUsernames();
        return SUCCESS;
    }

    public String show() {
        oneUser = userService.findById(42L);
        return SUCCESS;
    }

    public List<String> getUsers() { return users; }
    public void setUsers(List<String> users) { this.users = users; }
    public String getOneUser() { return oneUser; }
    public void setOneUser(String oneUser) { this.oneUser = oneUser; }
}