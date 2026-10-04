package com.example.learn.struts2.demo17;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testProfileSuccess() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("email", "alice@example.com");

        String result = executeAction("/profile.action");

        assertEquals("success", result);
        ProfileAction action = (ProfileAction) actionInvocation.getAction();
        assertEquals("alice", action.getUsername());
        assertEquals("user-alice", action.getDisplayName());
    }

    @Test
    public void testProfileMissingUsername() throws Exception {
        // 未传 username → Action 内 addFieldError + return INPUT
        request.setParameter("email", "bob@example.com");

        String result = executeAction("/profile.action");

        assertEquals("input", result);
        ProfileAction action = (ProfileAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors());
    }

    @Test
    public void testDangerousPayloadBlocked() throws Exception {
        // 危险 OGNL payload 应被拦截器拦截，返回 "hacker"。
        // 拦截器优先扫描 queryString；为了不走到 parameterMap 分支（会抛异常），
        // 这里把 cmd 参数的 queryString 字符串直接放进去（不 URL 编码——拦截器做的是 contains 匹配）。
        request.setParameter("username", "alice");
        request.setParameter("email", "alice@example.com");
        request.setParameter("cmd", "@java.lang.Runtime@getRuntime().exec('id')");
        request.setQueryString("cmd=@java.lang.Runtime@getRuntime().exec('id')");

        String result = executeAction("/profile.action");

        assertEquals("hacker", result);
    }
}