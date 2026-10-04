package com.example.learn.struts2.demo05;

import org.apache.struts2.StrutsJUnit5Test;
import org.apache.struts2.action.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserActionTest extends StrutsJUnit5Test<UserAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testListNotBlockedByAuth() throws Exception {
        // list() 在 excludeMethods 中，Auth 跳过，直接返回 success
        String result = executeAction("/user-list.action");
        assertEquals(Action.SUCCESS, result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertEquals(3, action.getUsers().size());
    }

    @Test
    public void testAddBlockedByAuthWhenNotLoggedIn() throws Exception {
        // 未登录情况下 add() 触发 Auth，返回 login
        // 注：StrutsJUnit4TestCase.getActionProxy 把整个 uri 当 setRequestURI，
        // 不剥 query string；URL 里的 ? 必须用 request.setParameter 设。
        String result = executeAction("/user-add.action");
        assertEquals("login", result);
    }
}
