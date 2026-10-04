package com.example.learn.struts2.demo14;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testEmptyUsernameFailsValidation() throws Exception {
        // username 空 → requiredstring 失败 → input
        // StrutsJUnit4TestCase 不剥 query string，改用 request.setParameter
        request.setParameter("age", "25");
        String result = executeAction("/user-save.action");
        assertEquals("input", result);
    }

    @Test
    public void testUsernameTooShortFailsValidation() throws Exception {
        // 长度 < 3 → stringlength 失败 → input
        request.setParameter("username", "ab");
        request.setParameter("age", "25");
        String result = executeAction("/user-save.action");
        assertEquals("input", result);
    }

    @Test
    public void testAgeOutOfRangeFailsValidation() throws Exception {
        // age 超出 [0,150] → int 校验失败 → input
        request.setParameter("username", "alice");
        request.setParameter("age", "200");
        String result = executeAction("/user-save.action");
        assertEquals("input", result);
    }

    @Test
    public void testValidInputsSucceed() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("age", "25");
        String result = executeAction("/user-save.action");
        assertEquals("success", result);
    }
}
