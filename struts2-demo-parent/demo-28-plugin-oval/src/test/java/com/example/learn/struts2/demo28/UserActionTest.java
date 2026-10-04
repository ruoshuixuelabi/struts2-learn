package com.example.learn.struts2.demo28;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserActionTest extends StrutsJUnit5Test {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testValidInput() throws Exception {
        String result = executeAction("/oval/save.action?username=alice&email=alice@example.com&nestedUser.username=bob&nestedUser.email=bob@example.com&nestedUser.phone=13800000000");
        assertEquals("success", result);
    }

    @Test
    public void testMissingUsernameShouldReturnInput() throws Exception {
        String result = executeAction("/oval/save.action?email=a@b.com");
        assertEquals("input", result);
    }
}