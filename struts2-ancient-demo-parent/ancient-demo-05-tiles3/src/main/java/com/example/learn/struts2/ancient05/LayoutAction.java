package com.example.learn.struts2.ancient05;

import com.opensymphony.xwork2.ActionSupport;

public class LayoutAction extends ActionSupport {
    private String title = "Tiles 3 Layout";

    public String execute() { return SUCCESS; }

    public String getTitle() { return title; }
}