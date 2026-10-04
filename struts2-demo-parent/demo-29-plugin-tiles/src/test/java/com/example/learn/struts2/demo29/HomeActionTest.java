package com.example.learn.struts2.demo29;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeActionTest extends StrutsJUnit5Test {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testHomeAction() throws Exception {
        String result = executeAction("/home.action");
        assertEquals("success", result);
    }

    @Test
    public void testUserListAction() throws Exception {
        String result = executeAction("/user-list.action");
        assertEquals("success", result);
    }
}