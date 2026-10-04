package com.example.learn.struts2.demo13;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PreparableActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testPrepareHookLoadsUser() throws Exception {
        // prepare!edit.action → prepare() 调用，user 加载完
        // StrutsJUnit4TestCase.getActionProxy 不剥 query string；参数改用 request.setParameter
        request.setParameter("id", "42");
        String result = executeAction("/preparable!edit.action");
        PreparableAction action = (PreparableAction) actionInvocation.getAction();
        assertEquals("success", result);
        assertNotNull(action.getUser());
        assertEquals(42L, action.getUser().getId());
    }
}