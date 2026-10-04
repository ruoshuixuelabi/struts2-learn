package com.example.learn.struts2.demo30;

import org.apache.struts2.ActionSupport;
import java.util.Arrays;
import java.util.List;

public class UserListAction extends ActionSupport {
    private List<String> users = Arrays.asList("Alice", "Bob", "Charlie");

    public String list() {
        return SUCCESS;
    }

    public List<String> getUsers() { return users; }
}