package com.example.learn.struts2.demo31;

import org.apache.struts2.ActionSupport;
import java.util.Arrays;
import java.util.List;

public class UserListAction extends ActionSupport {

    private List<User> users = Arrays.asList(
            new User("alice", "alice@example.com", true),
            new User("bob",   "bob@example.com",   false),
            new User("carol", "carol@example.com", false)
    );

    public String list() {
        return SUCCESS;
    }

    public List<User> getUsers() {
        return users;
    }
}