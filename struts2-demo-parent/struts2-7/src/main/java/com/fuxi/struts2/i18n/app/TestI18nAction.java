package com.fuxi.struts2.i18n.app;
import java.util.Arrays;
import java.util.Date;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
/**
 * @author admin
 */
@Getter
@Setter
public class TestI18nAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Date date = null;
	@Override
	public String execute(){
		date = new Date();
		//1. 在 Action 中访问国际化资源文件的 value 值
		String username = getText("username");
		System.out.println(username);
		//2. 带占位符的
		String time = getText("time", Arrays.asList(date));
		System.out.println(time);
		return SUCCESS;
	}
}
