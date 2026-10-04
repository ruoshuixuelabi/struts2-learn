package com.example.learn.struts2.demo13;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ModelDrivenActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testModelDrivenBindsParamsToModel() throws Exception {
        // StrutsJUnit4TestCase.getActionProxy 把整个 uri 当 setRequestURI，不剥 query string；
        // URL 里的 ? 必须用 request.setParameter 设。
        request.setParameter("id", "7");
        request.setParameter("name", "Bob");
        request.setParameter("age", "30");
        String result = executeAction("/model-driven!save.action");
        assertEquals("success", result);
        ModelDrivenAction action = (ModelDrivenAction) actionInvocation.getAction();
        assertNotNull(action.getUser());
        assertEquals(7L, action.getUser().getId());
        assertEquals("Bob", action.getUser().getName());
        assertEquals(30, action.getUser().getAge());
    }
}