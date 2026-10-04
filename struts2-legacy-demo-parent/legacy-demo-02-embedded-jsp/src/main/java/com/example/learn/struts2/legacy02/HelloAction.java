package com.example.learn.struts2.legacy02;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Embedded JSP demo action.
 *
 * Struts 2.5.30 提供嵌入式 JSP 引擎（struts2-embedded-jsp-plugin），
 * 让 Action 可以直接返回 JSP（无需 Servlet 容器编译 JSP），
 * 适合把 JSP 打包进 JAR 的场景。
 *
 * 6.0 弃用：维护成本高（内置 Jasper 与 Tomcat/Jetty 内置引擎重复）。
 */
public class HelloAction extends ActionSupport {

    private String name;

    public String execute() {
        return SUCCESS;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGreeting() {
        if (name == null || name.trim().isEmpty()) {
            return "World";
        }
        return name;
    }
}