package com.example.learn.struts2.demo03;

import org.apache.struts2.StrutsJUnit5Test;
import org.apache.struts2.action.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifecycleActionTest extends StrutsJUnit5Test<LifecycleAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testLifecycleAction() throws Exception {
        String result = executeAction("/lifecycle.action");
        assertEquals(Action.SUCCESS, result);
        LifecycleAction action = (LifecycleAction) actionInvocation.getAction();
        assertEquals("Lifecycle complete: Filter -> ActionProxy -> InterceptorStack -> Action -> Result",
                action.getMessage());
    }
}
