// ActionC.java
package com.example.learn.struts2.ancient03;
import com.opensymphony.xwork2.ActionSupport;
public class ActionC extends ActionSupport {
    private String msgC = "来自 ActionC（链的起点）";
    public String getMsgC() { return msgC; }
    public String execute() { return SUCCESS; }
}