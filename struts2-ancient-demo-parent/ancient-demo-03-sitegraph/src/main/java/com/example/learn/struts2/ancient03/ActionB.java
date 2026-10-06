// ActionB.java（依赖 ActionA 的 result）
package com.example.learn.struts2.ancient03;
import com.opensymphony.xwork2.ActionSupport;
public class ActionB extends ActionSupport {
    private String msgB = "来自 ActionB";
    public String getMsgB() { return msgB; }
    public String execute() { return SUCCESS; }
}