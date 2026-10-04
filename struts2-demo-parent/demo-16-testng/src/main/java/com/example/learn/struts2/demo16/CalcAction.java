package com.example.learn.struts2.demo16;

import org.apache.struts2.ActionSupport;

public class CalcAction extends ActionSupport {

    private Integer a;
    private Integer b;
    private String op; // add / sub / mul / div
    private Integer result;

    public String execute() {
        if (a == null || b == null) {
            addFieldError("a", "a 和 b 必须为整数");
            return INPUT;
        }
        switch (op == null ? "" : op) {
            case "add": result = a + b; break;
            case "sub": result = a - b; break;
            case "mul": result = a * b; break;
            case "div":
                if (b == 0) {
                    addActionError("除数不能为 0");
                    return INPUT;
                }
                result = a / b;
                break;
            default:
                addActionError("op 必须为 add/sub/mul/div");
                return INPUT;
        }
        return SUCCESS;
    }

    public Integer getA() { return a; }
    public void setA(Integer a) { this.a = a; }
    public Integer getB() { return b; }
    public void setB(Integer b) { this.b = b; }
    public String getOp() { return op; }
    public void setOp(String op) { this.op = op; }
    public Integer getResult() { return result; }
    public void setResult(Integer result) { this.result = result; }
}