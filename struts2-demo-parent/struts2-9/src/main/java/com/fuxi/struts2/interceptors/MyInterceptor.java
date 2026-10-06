package com.fuxi.struts2.interceptors;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 自定义Struts 2的拦截器
 * @author dep
 */
public class MyInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 6885322364905245537L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("before invocation.invoke...");
		//String result = invocation.invoke();
		System.out.println("after invocation.invoke...");
		return "success";
	}
}
