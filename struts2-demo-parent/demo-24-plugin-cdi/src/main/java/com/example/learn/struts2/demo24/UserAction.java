package com.example.learn.struts2.demo24;

import com.example.learn.struts2.demo24.model.User;
import jakarta.inject.Inject;
import org.apache.struts2.action.Action;

import java.util.List;

/**
 * Struts Action。
 * 字段 userService 用 @Inject 注入，由 CDI 容器（CDIObjectFactory）创建。
 *
 * 注意：Action 本身不必加 @Named / @RequestScoped，
 * struts2-cdi-plugin 通过 CDIObjectFactory 从容器获取 bean 实例。
 */
public class UserAction implements Action {

    @Inject
    private UserService userService;

    private List<User> users;
    private String injectedInfo;

    @Override
    public String execute() {
        users = userService.findAll();
        injectedInfo = "CDI 注入 UserService（@RequestScoped）成功";
        return SUCCESS;
    }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public String getInjectedInfo() { return injectedInfo; }
    public void setInjectedInfo(String injectedInfo) { this.injectedInfo = injectedInfo; }

    public UserService getUserService() { return userService; }
    public void setUserService(UserService userService) { this.userService = userService; }
}
