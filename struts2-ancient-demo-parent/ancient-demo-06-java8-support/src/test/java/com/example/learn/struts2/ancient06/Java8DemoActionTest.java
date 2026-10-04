package com.example.learn.struts2.ancient06;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class Java8DemoActionTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testJava8Action() throws Exception {
        String result = executeAction("/java8.action");
        assertEquals("success", result);
    }
}