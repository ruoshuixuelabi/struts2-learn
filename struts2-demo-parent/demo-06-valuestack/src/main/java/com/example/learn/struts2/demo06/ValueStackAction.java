package com.example.learn.struts2.demo06;

import org.apache.struts2.action.Action;
import org.apache.struts2.ActionContext;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.util.ValueStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValueStackAction extends ActionSupport {
    private String message;
    private List<String> items = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

    public String getMessage() {
        return message;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String execute() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        // 1. Action 属性（推荐）
        this.message = "Hello from Action property";
        // 2. push 临时对象到栈顶
        ExtraInfo info = new ExtraInfo("transient-info-from-stack");
        stack.push(info);
        // 3. set 直接放对象到栈（不增加栈深度）
        stack.set("greeting", "world-from-set");
        // 同时往 session 域放一个值，供 OGNL #session 访问
        ActionContext.getContext().getSession().put("userName", "Alice (from session)");
        return Action.SUCCESS;
    }
}