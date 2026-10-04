package com.example.learn.struts2.demo03;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.interceptor.AbstractInterceptor;

public class LifecycleTimerInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String actionName = invocation.getAction().getClass().getSimpleName();
        System.out.println("[TIMER]  >>> " + actionName + ".execute() 开始");
        long start = System.currentTimeMillis();
        String result = invocation.invoke();
        long cost = System.currentTimeMillis() - start;
        System.out.println("[TIMER]  <<< " + actionName + ".execute() 耗时 = " + cost + " ms, result = " + result);
        return result;
    }
}
