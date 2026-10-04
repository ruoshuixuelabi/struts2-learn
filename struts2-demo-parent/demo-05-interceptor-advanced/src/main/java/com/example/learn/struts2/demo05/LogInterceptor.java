package com.example.learn.struts2.demo05;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.interceptor.AbstractInterceptor;

public class LogInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String actionName = invocation.getAction().getClass().getSimpleName();
        String method = invocation.getProxy().getMethod();
        System.out.println("[LOG] >>> " + actionName + "#" + method + "()");
        String result = invocation.invoke();
        System.out.println("[LOG] <<< " + actionName + "#" + method + "() result=" + result);
        return result;
    }
}
