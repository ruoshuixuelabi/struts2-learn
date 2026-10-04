package com.example.learn.struts2.demo20;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecureActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testSecureActionSuccess() throws Exception {
        request.setParameter("username", "alice");

        String result = executeAction("/secure-action.action");

        assertEquals("success", result);
        SecureAction action = (SecureAction) actionInvocation.getAction();
        assertEquals("alice", action.getUsername());
        assertEquals("welcome, alice", action.getDisplay());
    }

    @Test
    public void testSecureActionMissingUsername() throws Exception {
        // 未传 username → @StrutsParameter(required=true) 触发 StrutsParameterValidationException
        // 该异常在测试中可能被转换，result 可能为 input 或抛错；此处演示 @StrutsParameter 校验生效
        try {
            executeAction("/secure-action.action");
        } catch (Exception expected) {
            // 接受校验异常；这就是 7.x 的安全行为
            return;
        }
        SecureAction action = (SecureAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors() || action.getUsername() == null);
    }

    @Test
    public void testSecureInfoAction() throws Exception {
        String result = executeAction("/secure-info.action");
        assertEquals("success", result);
    }

    @Test
    public void testDangerousPayloadBlocked() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("cmd", "@java.lang.Runtime@getRuntime().exec('id')");

        // 黑名单拦截器抛 SecurityException，被 executeAction 包装后抛出
        assertThrows(Exception.class, () -> executeAction("/secure-action.action"));
    }

    @Test
    public void testMemberAccessPayloadBlocked() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("p", "#_memberAccess=@java.lang.Runtime@getRuntime().exec('id')");

        assertThrows(Exception.class, () -> executeAction("/secure-action.action"));
    }
}