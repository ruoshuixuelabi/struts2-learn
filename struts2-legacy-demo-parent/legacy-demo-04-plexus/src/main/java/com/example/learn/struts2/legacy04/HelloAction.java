package com.example.learn.struts2.legacy04;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Plexus demo action。
 *
 * struts2-plexus-plugin 把 PlexusObjectFactory 注册为 Struts 的 ObjectFactory，
 * 让 Action 由 Plexus 容器管理生命周期，依赖的 UserService 也由 Plexus 注入。
 *
 * 6.0 弃用：Plexus 项目本身停滞（Eclipse Sisu 接管），Spring 已是 IoC 事实标准。
 */
public class HelloAction extends ActionSupport {

    /** Plexus 注入的 UserService（不是 Spring @Autowired） */
    private UserService userService;

    private String name;

    public String execute() {
        return SUCCESS;
    }

    public String getMessage() {
        return userService.greet(name == null ? "World" : name);
    }

    public UserService getUserService() { return userService; }
    public void setUserService(UserService userService) { this.userService = userService; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}