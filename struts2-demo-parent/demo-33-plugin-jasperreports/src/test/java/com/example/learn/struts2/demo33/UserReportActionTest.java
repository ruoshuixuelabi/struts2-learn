package com.example.learn.struts2.demo33;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserReportActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testUserListActionReturnsSuccess() throws Exception {
        String result = executeAction("/report/user-list.action");
        assertEquals("success", result);

        UserReportAction action = (UserReportAction) actionInvocation.getAction();
        assertNotNull(action.getUsers());
        assertEquals(5, action.getUsers().size());
        assertEquals("alice", action.getUsers().get(0).getUsername());
    }

    @Test
    public void testUserListActionSupportsXlsResultName() throws Exception {
        // 切换到 xls result 配置（同名 action 三个 result，按 resultName 匹配）
        String result = executeAction("/report/user-list.action?resultName=xls");
        assertEquals("xls", result);
    }

    @Test
    public void testDynamicActionCompilesJrxml() throws Exception {
        String result = executeAction("/report/dynamic.action");
        assertEquals("success", result);

        DynamicReportAction action = (DynamicReportAction) actionInvocation.getAction();
        assertNotNull(action.getJasperPrint());
        assertNotNull(action.getJasperPrint().getPages());
        assertTrue(action.getJasperPrint().getPages().size() >= 1);
    }
}