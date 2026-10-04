package com.example.learn.struts2.demo05;

import org.apache.struts2.ActionSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAction extends ActionSupport {

    private List<String> users = new ArrayList<>(Arrays.asList("Alice", "Bob", "Carol"));
    private String userName;

    public List<String> getUsers() {
        return users;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 读操作：不被 Auth 拦截（excludeMethods） */
    public String list() {
        System.out.println("[UserAction] list() - " + users.size() + " users");
        return SUCCESS;
    }

    /** 读操作：不被 Auth 拦截 */
    public String view() {
        System.out.println("[UserAction] view() - userName=" + userName);
        return SUCCESS;
    }

    /** 写操作：被 Auth 拦截（includeMethods） */
    public String add() {
        System.out.println("[UserAction] add() - " + userName);
        users.add(userName);
        return SUCCESS;
    }

    /** 写操作：被 Auth 拦截 */
    public String update() {
        System.out.println("[UserAction] update() - " + userName);
        return SUCCESS;
    }

    /** 写操作：被 Auth 拦截 */
    public String delete() {
        System.out.println("[UserAction] delete() - " + userName);
        users.remove(userName);
        return SUCCESS;
    }
}
