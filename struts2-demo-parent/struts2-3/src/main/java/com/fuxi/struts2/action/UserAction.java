package com.fuxi.struts2.action;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
public class UserAction implements SessionAware, ApplicationAware {
	private String username;
	public void setUsername(String username) {
		this.username = username;
	}
	@SuppressWarnings("rawtypes")
	public String logout() {
		// 1.在线人数-1:获取在线人数若数量还大于0则-1
		Integer count = (Integer) application.get("count");
		if (count != null && count > 0) {
			count--;
			//减完之后记得放回去
			application.put("count", count);
		}
		// 2.session失效,先强转为SessionMap,再调用invalidate方法
		((SessionMap) session).invalidate();
		return "logout-success";
	}
	public String execute() {
		// 把用户信息存入 Session 域中
		// 1.获取session,通过把用户信息存入 Session 域中,SessionAware接口
		// 2.获取登录信息:通过在Action中添加setter方法
		// 3.把用户信息放入session中
		session.put("username", username);
		// 在线人数+1
		// 1.获取当前的在线人数
		Integer count = (Integer) application.get("count");
		if (count == null) {
			count = 0;
		}
		// 2.使当前的在线人数+1
		count++;
		application.put("count", count);
		return "login-success";
	}
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	private Map<String, Object> application;
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
