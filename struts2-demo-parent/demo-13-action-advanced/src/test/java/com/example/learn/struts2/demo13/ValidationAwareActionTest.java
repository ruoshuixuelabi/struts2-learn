package com.example.learn.struts2.demo13;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationAwareActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testMissingFieldsReturnsInput() throws Exception {
        // 不传任何参数 → username/age 都空 → addFieldError → input
        String result = executeAction("/validation-aware!check.action");
        assertEquals("input", result);
    }

    @Test
    public void testValidFieldsReturnsSuccess() throws Exception {
        // StrutsJUnit4TestCase.getActionProxy 不剥 query string；参数改用 request.setParameter
        request.setParameter("username", "alice");
        request.setParameter("age", "25");
        String result = executeAction("/validation-aware!check.action");
        assertEquals("success", result);
    }
}