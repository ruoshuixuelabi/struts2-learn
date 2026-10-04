package com.example.learn.struts2.demo21;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConventionActionTest extends StrutsJUnit5Test {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testConventionAction() throws Exception {
        String result = executeAction("/convention/hello.action");
        assertEquals("success", result);
    }
}