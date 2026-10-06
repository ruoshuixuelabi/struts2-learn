package com.example.learn.struts2.ancient02;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "msg")
@RequestScoped
public class MessageBean {
    public String getText() {
        return "来自 JSF ManagedBean (Mojarra 2.1.29)";
    }
}