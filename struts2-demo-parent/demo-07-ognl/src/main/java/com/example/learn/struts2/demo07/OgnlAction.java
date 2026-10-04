package com.example.learn.struts2.demo07;

import org.apache.struts2.action.Action;
import org.apache.struts2.ActionContext;
import org.apache.struts2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class OgnlAction extends ActionSupport {

    private String message = "hello ognl";
    private double price = 19.9;
    private int quantity = 3;
    private List<User> users = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String execute() {
        users.add(new User("Alice", 25));
        users.add(new User("Bob", 17));
        users.add(new User("Carol", 30));
        users.add(new User("Dave", 15));

        // 模拟往 request / session 域放数据
        ActionContext.getContext().put("fromRequest", "request-scope-value");
        ActionContext.getContext().getSession().put("userName", "Alice (session)");

        return Action.SUCCESS;
    }
}
