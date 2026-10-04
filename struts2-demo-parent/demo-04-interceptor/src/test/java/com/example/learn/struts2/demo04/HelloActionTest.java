package com.example.learn.struts2.demo04;

import org.apache.struts2.StrutsJUnit5Test;
import org.apache.struts2.action.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloActionTest extends StrutsJUnit5Test<HelloAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testHelloAction() throws Exception {
        String result = executeAction("/hello.action");
        assertEquals(Action.SUCCESS, result);
        HelloAction action = (HelloAction) actionInvocation.getAction();
        assertEquals("Hello from Demo 04 (basic interceptor)", action.getMessage());
    }
}
