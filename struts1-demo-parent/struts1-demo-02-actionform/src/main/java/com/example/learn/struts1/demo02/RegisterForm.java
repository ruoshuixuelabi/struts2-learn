package com.example.learn.struts1.demo02;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class RegisterForm extends ActionForm {
    private String username;
    private String email;
    private int age;

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (username == null || username.length() < 3) {
            errors.add("username", new ActionMessage("error.username.required"));
        }
        if (email == null || !email.contains("@")) {
            errors.add("email", new ActionMessage("error.email.invalid"));
        }
        if (age < 0 || age > 150) {
            errors.add("age", new ActionMessage("error.age.range"));
        }
        return errors;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.age = 0;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
