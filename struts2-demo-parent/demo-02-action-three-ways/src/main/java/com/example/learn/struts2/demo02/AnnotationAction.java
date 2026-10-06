package com.example.learn.struts2.demo02;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Namespace("/annotation")
@Action("annotation")   // 不带斜杠，和 @Namespace 拼接
@Results(@Result(name = "success", location = "/annotation-result.jsp"))
public class AnnotationAction {
    public String execute() {
        return "success";
    }
}