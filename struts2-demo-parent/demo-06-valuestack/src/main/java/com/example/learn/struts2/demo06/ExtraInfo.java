package com.example.learn.struts2.demo06;

import org.apache.struts2.interceptor.parameter.StrutsParameter;

/**
 * push 到栈顶的临时对象（值栈 CompoundRoot）
 */
public class ExtraInfo {
    private String label;

    public ExtraInfo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @StrutsParameter
    public void setLabel(String label) {
        this.label = label;
    }
}