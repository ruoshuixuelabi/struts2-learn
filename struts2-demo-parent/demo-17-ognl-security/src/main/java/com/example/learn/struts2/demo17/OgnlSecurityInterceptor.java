package com.example.learn.struts2.demo17;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.AbstractInterceptor;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

/**
 * 自定义 OGNL 安全拦截器：
 * 检查 query string / 参数值中是否包含已知危险 OGNL 字符串。
 * 命中 → 返回 "hacker"，跳到 hacker.jsp。
 */
public class OgnlSecurityInterceptor extends AbstractInterceptor implements StrutsStatics {

    private static final Set<String> DANGEROUS_KEYWORDS = Set.of(
        "@java.lang.Runtime",
        "@java.lang.ProcessBuilder",
        "@java.lang.System",
        "#_memberAccess",
        "Runtime.getRuntime",
        "ProcessBuilder",
        "exec(",
        "getClass().forName"
    );

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(HTTP_REQUEST);

        String queryString = request.getQueryString();
        if (queryString != null) {
            String lower = queryString.toLowerCase();
            for (String danger : DANGEROUS_KEYWORDS) {
                if (lower.contains(danger.toLowerCase())) {
                    return "hacker";
                }
            }
        }

        // 也扫描所有参数值
        request.getParameterMap().forEach((key, values) -> {
            for (String v : values) {
                if (v == null) continue;
                String lower = v.toLowerCase();
                for (String danger : DANGEROUS_KEYWORDS) {
                    if (lower.contains(danger.toLowerCase())) {
                        throw new SecurityException("dangerous ognl payload in param: " + key);
                    }
                }
            }
        });

        return invocation.invoke();
    }
}