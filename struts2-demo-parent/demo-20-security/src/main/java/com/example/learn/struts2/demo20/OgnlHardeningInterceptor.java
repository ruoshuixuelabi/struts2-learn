package com.example.learn.struts2.demo20;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.AbstractInterceptor;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * OGNL 安全加固拦截器：黑名单 + 正则匹配，覆盖 RCE 漏洞常用 payload。
 */
public class OgnlHardeningInterceptor extends AbstractInterceptor implements StrutsStatics {

    private static final Set<String> DANGEROUS_KEYWORDS = Set.of(
        "@java.lang.Runtime",
        "@java.lang.ProcessBuilder",
        "@java.lang.System",
        "@java.lang.Class",
        "Runtime.getRuntime",
        "ProcessBuilder",
        "exec(",
        "getClass().forName",
        "Class.forName",
        "#_memberAccess",
        "#context",
        "#_val",
        "#_root",
        "new java.lang.ProcessBuilder"
    );

    // 启发式：检测可疑的 OGNL 方法调用模式
    private static final Pattern SUSPICIOUS = Pattern.compile(
        "(@[a-zA-Z_][\\w.]*){2,}"  // 多个 @ 类引用串联
        + "|#[_\\w]+\\s*=\\s*\\("   // 赋值给 #_xxx
    );

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(HTTP_REQUEST);

        if (request.getQueryString() != null) {
            check(request.getQueryString(), "query");
        }

        var paramMap = request.getParameterMap();
        for (var entry : paramMap.entrySet()) {
            for (String v : entry.getValue()) {
                if (v != null) {
                    check(v, "param " + entry.getKey());
                }
            }
        }

        return invocation.invoke();
    }

    private void check(String input, String source) {
        String lower = input.toLowerCase();
        for (String danger : DANGEROUS_KEYWORDS) {
            if (lower.contains(danger.toLowerCase())) {
                throw new SecurityException("OGNL hardening: blocked " + danger + " in " + source);
            }
        }
        if (SUSPICIOUS.matcher(input).find()) {
            throw new SecurityException("OGNL hardening: blocked suspicious pattern in " + source);
        }
    }
}