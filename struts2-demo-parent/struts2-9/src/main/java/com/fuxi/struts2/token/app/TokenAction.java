package com.fuxi.struts2.token.app;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TokenAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String username;

	@Override
	public String execute() throws Exception {
		//模拟一下延迟的效果,这样容易测试表单的重复提交问题
		Thread.sleep(2000);
		System.out.println(username);
		return SUCCESS;
	}
}
