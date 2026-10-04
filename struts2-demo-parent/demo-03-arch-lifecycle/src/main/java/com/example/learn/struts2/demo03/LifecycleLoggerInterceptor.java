package com.example.learn.struts2.demo03;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.interceptor.Interceptor;

public class LifecycleLoggerInterceptor implements Interceptor {

    @Override
    public void init() {
        System.out.println("[LOGGER] init() 拦截器初始化");
    }

    @Override
    public void destroy() {
        System.out.println("[LOGGER] destroy() 拦截器销毁");
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String actionName = invocation.getAction().getClass().getSimpleName();
        System.out.println("[LOGGER] >>> " + actionName + ".execute() 开始");
        String result = invocation.invoke();
        System.out.println("[LOGGER] <<< " + actionName + ".execute() 结束，result = " + result);
        return result;
    }
}
