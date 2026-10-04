package com.example.learn.struts2.legacy07;

import com.opensymphony.xwork2.ActionSupport;

/**
 * DWR 集成 Action。
 * 通过 struts.xml 中 <result type="dxr"> 把 Action 的某个属性直接
 * 以 DWR 远程对象的形式暴露给前端 JavaScript。
 *
 * 这种"Action + dwr result"模式是 struts2-dwr-plugin 的典型用法：
 * 比单纯 DWR 配置更灵活（可复用拦截器、国际化等 Struts 特性）。
 */
public class GreetAction extends ActionSupport {

    private String name = "Struts2";
    private GreetService service = new GreetService();

    /** 进入 DWR 暴露的 Action（remote method） */
    public String hello() {
        return SUCCESS;
    }

    /** 演示直接调用 service 方法（前端会用 DWR 调用此 Action） */
    public String getMessage() {
        return service.greet(name);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public GreetService getService() { return service; }
    public void setService(GreetService service) { this.service = service; }
}
