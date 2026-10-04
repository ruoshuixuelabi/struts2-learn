package com.example.learn.struts2.demo22;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 验证 UserAction 返回 SUCCESS；具体 JSON 序列化为 JSONResult 行为，
 * 由 struts2-json-plugin 内部处理（不通过单元测试验证序列化结果）。
 */
public class UserActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testUserApi() throws Exception {
        request.setParameter("userId", "1");
        String result = executeAction("/user-api.action");
        assertEquals("success", result);

        UserAction action = (UserAction) actionInvocation.getAction();
        assertNotNull(action.getUser());
        assertEquals("alice", action.getUser().getUsername());
    }
}
