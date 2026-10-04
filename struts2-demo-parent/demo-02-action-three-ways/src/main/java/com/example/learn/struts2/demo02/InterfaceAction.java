package com.example.learn.struts2.demo02;

import org.apache.struts2.action.Action;

public class InterfaceAction implements Action {
    @Override
    public String execute() {
        return SUCCESS;
    }
}