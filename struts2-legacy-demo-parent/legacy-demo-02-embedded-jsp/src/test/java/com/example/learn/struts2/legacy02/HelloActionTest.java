package com.example.learn.struts2.legacy02;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloActionTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testGreeting() {
        HelloAction action = new HelloAction();
        action.setName("Struts");
        assertEquals("Struts", action.getGreeting());
    }

    @Test
    public void testDefaultGreeting() {
        HelloAction action = new HelloAction();
        assertEquals("World", action.getGreeting());
    }

    @Test
    public void testExecute() throws Exception {
        request.setParameter("name", "alice");
        String result = executeAction("/hello.action");
        assertEquals("success", result);
    }
}