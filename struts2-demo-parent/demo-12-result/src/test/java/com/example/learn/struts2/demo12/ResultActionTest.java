package com.example.learn.struts2.demo12;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testDispatcherAction() throws Exception {
        String result = executeAction("/dispatcher.action");
        assertEquals("success", result);
    }

    @Test
    public void testRedirectAction() throws Exception {
        String result = executeAction("/redirect.action");
        assertEquals("success", result);
    }

    @Test
    public void testChainFlow() throws Exception {
        String firstResult = executeAction("/chain-first.action");
        assertEquals("chain-next", firstResult);
        String secondResult = executeAction("/chain-second.action");
        assertEquals("success", secondResult);
    }

    @Test
    public void testJsonAction() throws Exception {
        String result = executeAction("/json.action");
        assertEquals("success", result);
    }
}