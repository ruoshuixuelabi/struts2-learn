package com.example.learn.struts2.demo18;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testUserSaveSuccess() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("email", "alice@example.com");
        request.setParameter("age", "25");

        String result = executeAction("/user-save.action");

        assertEquals("success", result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertEquals("alice", action.getUsername());
        assertTrue(action.getMessage().contains("alice"));
    }

    @Test
    public void testUserSaveInvalidEmail() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("email", "not-an-email");
        request.setParameter("age", "25");

        String result = executeAction("/user-save.action");

        // @Email 校验失败 → validation 拦截器返回 input
        assertEquals("input", result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("email"));
    }

    @Test
    public void testUserSaveAgeOutOfRange() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("email", "alice@example.com");
        request.setParameter("age", "999");

        String result = executeAction("/user-save.action");

        assertEquals("input", result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors());
        assertNotNull(action.getFieldErrors().get("age"));
    }

    @Test
    public void testPersonSaveSuccess() throws Exception {
        request.setParameter("name", "张三");
        request.setParameter("age", "30");

        String result = executeAction("/person-save.action");

        assertEquals("success", result);
    }

    @Test
    public void testPersonSaveMissingName() throws Exception {
        request.setParameter("age", "30");

        String result = executeAction("/person-save.action");

        assertEquals("input", result);
        PersonAction action = (PersonAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors());
    }
}