package com.fuxi.struts.valuestack;
import java.util.List;
import lombok.Data;
@Data
public class UserAction {
	private String userId;
	private String userName;
	private String password;
	private String desc;
	private boolean married=true;
	private String gender;
	//这里不能用数组,用集合才能回显,数组的话能接收到值但是不回显
	private List<String> city;
	private String age;

	public String save(){
		System.out.println(this);
//		UserAction userAction=new UserAction();
//		userAction.setDesc("1212121212");
//		ActionContext.getContext().getValueStack().push(userAction);
		return "input";
	}
}
