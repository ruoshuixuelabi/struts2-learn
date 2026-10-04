package com.example.learn.struts1.demo01;

import org.apache.struts.action.ActionForm;

/**
 * Struts 1 的 Form Bean。
 * 对比 Struts 2：Struts 2 没有 ActionForm，Action 属性直接接收。
 */
public class HelloForm extends ActionForm {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
