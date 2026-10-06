// ActionA.java
package com.example.learn.struts2.ancient03;
import com.opensymphony.xwork2.ActionSupport;
public class ActionA extends ActionSupport {
    private String msgA = "来自 ActionA";
    public String getMsgA() { return msgA; }
    public String execute() { return SUCCESS; }
}