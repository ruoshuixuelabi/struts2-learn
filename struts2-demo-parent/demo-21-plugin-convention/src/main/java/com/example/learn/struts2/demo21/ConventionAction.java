package com.example.learn.struts2.demo21;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Namespace("/convention")
@Action("/convention/hello")
@Results({
    @Result(name = "success", location = "/convention-result.jsp")
})
public class ConventionAction {
    private String message = "Convention Plugin 演示";

    public String execute() {
        return "success";
    }

    public String getMessage() { return message; }
}