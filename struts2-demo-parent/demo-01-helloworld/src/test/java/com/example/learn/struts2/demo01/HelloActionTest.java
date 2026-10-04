package com.example.learn.struts2.demo01;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.struts2.action.Action;

public class HelloActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testHelloAction() throws Exception {
        String result = executeAction("/hello.action");
        assertEquals(Action.SUCCESS, result);
        HelloAction action = (HelloAction) actionInvocation.getAction();
        assertEquals("Hello, Struts 7.3.0!", action.getMessage());
    }
}