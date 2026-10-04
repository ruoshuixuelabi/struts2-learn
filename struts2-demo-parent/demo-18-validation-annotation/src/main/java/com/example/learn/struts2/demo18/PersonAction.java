package com.example.learn.struts2.demo18;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.validator.annotations.StringLengthFieldValidator;
import org.apache.struts2.validator.annotations.IntRangeFieldValidator;

/**
 * 第二个 Action，演示纯 Struts 注解（不依赖 Bean Validation 插件）。
 * 通过方法前缀触发不同方法。
 */
public class PersonAction extends ActionSupport {

    // Struts 7.x：FieldValidator 注解的 @Target 改成 METHOD，必须放在 getter 上
    // （不能放在字段上；旧 API 已不再支持 FIELD 目标）
    private String name;
    private Integer age;

    private String message;

    public String save() {
        message = "Person saved: " + name + " (age=" + age + ")";
        return SUCCESS;
    }

    @RequiredStringValidator(message = "姓名必填")
    @StringLengthFieldValidator(minLength = "2", maxLength = "10", message = "姓名长度 2-10")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @IntRangeFieldValidator(min = "0", max = "150", message = "年龄 0-150")
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}