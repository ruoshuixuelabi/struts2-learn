package com.fuxi.struts2.validation.app;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TestValidationAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer age;
	private String password;
	private String password2;
	private Integer count;//定义成Integer为了在前台不回显为0
	private String idCard;
	@Override
	public String execute() throws Exception {
		System.out.println("age: " + age);
		return SUCCESS;
	}
}
