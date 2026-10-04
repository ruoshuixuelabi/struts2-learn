package com.example.learn.struts2.demo31;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserListActionTest extends StrutsJUnit5Test {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testUserListAction() throws Exception {
        String result = executeAction("/user-list.action");
        assertEquals("success", result);
    }

    @Test
    public void testEmailPreviewAction() throws Exception {
        String result = executeAction("/email-preview.action");
        assertEquals("success", result);
    }
}