package com.example.learn.struts2.legacy05;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Portlet demo action。
 *
 * struts2-portlet-plugin 让 Struts 2 Action 在 JSR-168 / JSR-286
 * Portal 容器（WebSphere Portal / Liferay / Pluto）中运行。
 *
 * Action 必须继承 PortletAction 才能区分 render / action / resource 三阶段。
 * 这里为了简洁展示 ActionSupport，完整实现应继承 PortletAction。
 *
 * 6.0 弃用 + 7.x 移除（Portal 时代过去）。
 */
public class HelloAction extends ActionSupport {

    private String name;

    public String execute() {
        return SUCCESS;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGreeting() {
        return "Hello from Portlet, " + (name == null ? "World" : name);
    }
}