package com.example.learn.struts2.demo08;

import org.apache.struts2.StrutsJUnit5Test;
import org.apache.struts2.action.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFormActionTest extends StrutsJUnit5Test<UserFormAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testInput() throws Exception {
        String result = executeAction("/input.action");
        assertEquals(Action.INPUT, result);
        UserFormAction action = (UserFormAction) actionInvocation.getAction();
        assertEquals(4, action.getCities().size());
    }

    @Test
    public void testSave() throws Exception {
        // 通过 request 参数模拟表单提交
        request.setParameter("user.name", "Alice");
        request.setParameter("user.age", "25");
        request.setParameter("user.city", "BJ");
        String result = executeAction("/user-save.action");
        assertEquals(Action.SUCCESS, result);
    }
}
