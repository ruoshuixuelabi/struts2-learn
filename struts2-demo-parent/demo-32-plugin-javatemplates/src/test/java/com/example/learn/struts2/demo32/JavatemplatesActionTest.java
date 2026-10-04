package com.example.learn.struts2.demo32;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavatemplatesActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testExecuteProducesTemplateString() throws Exception {
        String result = executeAction("/javatemplates/plain.action");
        assertEquals("success", result);

        JavatemplatesAction action = (JavatemplatesAction) actionInvocation.getAction();
        assertNotNull(action.getTemplate());
        assertTrue(action.getTemplate().contains("Javatemplates 插件演示"));
        assertTrue(action.getTemplate().contains("<!DOCTYPE html>"));
    }
}