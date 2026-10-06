package com.fuxi.struts2.action;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
public class TestServletAwareTest  implements ServletRequestAware,ServletContextAware,ServletResponseAware{
	/*
	 * 通过实现ServletXXXAware接口的方式可以由Struts 2注入需要的Servlet相关的对象
	 * ServletRequestAware:注入HttpServletRequest对象(比较常用)
	 * ServletContextAware:注入ServletContext对象(比较常用)
	 * ServletResponseAware:注入HttpServletResponse对象
	 */
	private ServletContext context;
	@Override
	public void setServletContext(ServletContext context) {
		System.out.println(context);
		this.context=context;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println(request);
 	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		System.out.println(response);
	}
	public String execute(){
		System.out.println(context);
		return "success";
	}
}
