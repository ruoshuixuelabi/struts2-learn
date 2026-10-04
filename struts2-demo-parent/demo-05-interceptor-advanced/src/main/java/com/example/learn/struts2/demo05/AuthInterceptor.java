package com.example.learn.struts2.demo05;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.interceptor.MethodFilterInterceptor;

public class AuthInterceptor extends MethodFilterInterceptor {

    private String requiredRole;

    public void setRequiredRole(String requiredRole) {
        this.requiredRole = requiredRole;
    }

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        // 只对 includeMethods 中的方法生效；excludeMethods 中的方法跳过
        Object user = invocation.getInvocationContext().getSession().get("user");
        if (user == null) {
            System.out.println("[AUTH] 未登录，跳到 login");
            return "login";
        }
        if (requiredRole != null) {
            System.out.println("[AUTH] 已登录，要求角色=" + requiredRole);
        }
        return invocation.invoke();
    }
}
