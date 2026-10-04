package com.example.learn.struts2.ancient03;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionCTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testChainAction() throws Exception {
        String result = executeAction("/actionC.action");
        assertEquals("success", result);
    }
}