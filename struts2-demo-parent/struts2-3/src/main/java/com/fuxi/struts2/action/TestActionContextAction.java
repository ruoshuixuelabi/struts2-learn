package com.fuxi.struts2.action;
import java.util.Map;

import org.apache.struts2.ActionContext;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.dispatcher.SessionMap;
public class TestActionContextAction {
	public String execute() {
		// 0. 获取ActionContext对象,ActionContext是Action的上下文对象,可以从中获取到当往Action需要的一切信息
		ActionContext actionContext = ActionContext.getContext();
		// 1. 获取 application 对应的 Map,并向其中添加一个属性
		// 通过调用 ActionContext 对象的 getApplication() 方法来获取 application 对象的 Map 对象
		Map<String, Object> applicationMap = actionContext.getApplication();
		// 设置属性
		applicationMap.put("applicationKey", "applicationValue");
		// 获取属性
		Object date = applicationMap.get("date");
		System.out.println("date: " + date);
		// 2. session
		Map<String, Object> sessionMap = actionContext.getSession();
		sessionMap.put("sessionKey", "sessionValue");
		//通过此处的打印我们可以知道这个类型是SessionMap类型的
		System.out.println(sessionMap.getClass());
		if (sessionMap instanceof SessionMap sm) {
//			SessionMap sm = (SessionMap)sessionMap;
			//由于Map的类型是SessionMap的,因此此处可以强转

            //			SessionMap<String, Object> sm = (SessionMap<String,Object>)sessionMap;
			sm.invalidate();
			System.out.println("session 失效了");
		}
		// 3. request*
		/**
		 * ActionContext中并没有提供getRequest()方法来获取request对应的Map,
		 * 因此需要手工调用get()方法,传入request字符串来获取
		 */
		@SuppressWarnings("unchecked")
		Map<String,Object> requestMap = (Map<String,Object>) actionContext.get("request");
		requestMap.put("requestKey", "requestValue");
		// 4. 获取请求参数对应的Map,并获取指定的参数值。键:请求参数的名字,值: 请求参数的值对应的字符串数组
		// 注意: 1. getParameters的返回值为Map<String, Object>,而不是 Map<String,String[]>
		// 2. parameters这个Map只能读,不能写入数据,如果写入,并不出错,但也不起作用!
		HttpParameters parameters = actionContext.getParameters();
		Parameter name = parameters.get("name");
		System.out.println("name: " + name);

//		Map<String, Object> parameters = actionContext.getParameters();
//		System.out.println(((String[]) parameters.get("name"))[0]);
//		parameters.put("age", 100);
		return "success";
	}
}
