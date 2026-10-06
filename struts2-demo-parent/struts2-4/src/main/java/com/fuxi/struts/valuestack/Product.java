package com.fuxi.struts.valuestack;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import lombok.Data;
@Data
public class Product  implements RequestAware,SessionAware{
	private Integer productId;
	private String productName;
	private String productDesc;
	private double productPrice;
	public String testTag(){
		this.productId=001;
		this.productName="name";
		this.productDesc="desc";
		this.productPrice=1000;
		return "success";
	}
	public String save(){
		System.out.println("save: " + this);
		//1. 获取值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		//2. 创建 Test 对象,并为其属性赋值
		Test object = new Test();
		object.setProductDesc("AABBCCDD");
		object.setProductName("ABCD");
		//3. 把Test 对象压入到值栈的栈顶!
		valueStack.push(object);
		sessionmap.put("product", this);
		requestmap.put("test", object);
		int i=10/0;//模拟出现异常
		System.out.println(i);
		return "success";
	}
	private  Map<String, Object> sessionmap;
	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionmap=session;
	}
	private Map<String, Object> requestmap;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestmap=arg0;
	}
}
