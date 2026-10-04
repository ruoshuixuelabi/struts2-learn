package com.example.learn.struts2.legacy01;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserActionTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testValidUser() throws Exception {
        request.setParameter("user.username", "alice");
        request.setParameter("user.email", "alice@example.com");
        String result = executeAction("/user.action");
        assertEquals("success", result);
    }
}