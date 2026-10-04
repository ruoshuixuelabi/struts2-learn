package com.example.learn.struts2.demo10;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testHomeAction() throws Exception {
        String result = executeAction("/home.action");
        assertEquals("success", result);
    }

    @Test
    public void testUserDeleteWithoutIdThrowsIllegalArgument() throws Exception {
        // Action 级 IllegalArgumentException → input result
        String result = executeAction("/user!delete.action");
        assertEquals("input", result);
    }
}