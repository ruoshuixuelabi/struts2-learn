package com.example.learn.struts2.demo04;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.interceptor.AbstractInterceptor;

public class MyLoggerInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String name = invocation.getAction().getClass().getSimpleName();
        System.out.println(">>> " + name + ".execute() 开始");
        long start = System.currentTimeMillis();
        String result = invocation.invoke();
        long cost = System.currentTimeMillis() - start;
        System.out.println("<<< " + name + ".execute() 结束，result=" + result + "，耗时=" + cost + "ms");
        return result;
    }
}
