package com.example.learn.struts2.ancient02;

import com.opensymphony.xwork2.ActionSupport;

public class JsfBridgeAction extends ActionSupport {
    private String jsfOutcome;

    public String execute() {
        // 演示 Struts 调用 JSF managed bean
        // 2.3.x 时代 JSF 桥接通过 JSF 1.x 的 javax.faces.context.FacesContext
        jsfOutcome = "JSF 桥接调用成功（Struts 2 Action -> JSF FacesContext）";
        return SUCCESS;
    }

    public String getJsfOutcome() { return jsfOutcome; }
}