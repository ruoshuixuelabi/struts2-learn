package com.example.learn.struts2.demo07;

import org.apache.struts2.StrutsJUnit5Test;
import org.apache.struts2.action.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OgnlActionTest extends StrutsJUnit5Test<OgnlAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testOgnlAction() throws Exception {
        String result = executeAction("/ognl.action");
        assertEquals(Action.SUCCESS, result);
        OgnlAction action = (OgnlAction) actionInvocation.getAction();
        assertEquals(4, action.getUsers().size());
        assertEquals(19.9, action.getPrice());
    }
}
