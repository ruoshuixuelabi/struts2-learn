package com.example.learn.struts2.demo19;

import jakarta.servlet.ServletException;
import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import com.example.learn.struts2.demo19.action.UserAction;
import com.example.learn.struts2.demo19.service.SimpleUserService;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    /**
     * 测试环境不走 Spring DI；这里用反射给 Action 的 @Autowired userService 字段塞一个实例。
     * demo-19 struts.xml 配置 struts.objectFactory=struts（默认），Action 由 Struts 直接 new。
     */
    @Override
    protected String executeAction(String uri) throws ServletException, UnsupportedEncodingException {
        // 拿到 ActionProxy 后注入依赖再 execute
        org.apache.struts2.ServletActionContext.setRequest(this.request);
        org.apache.struts2.ServletActionContext.setResponse(this.response);
        org.apache.struts2.ActionProxy proxy = getActionProxy(uri);
        Object action = proxy.getAction();
        if (action instanceof UserAction) {
            try {
                Field f = UserAction.class.getDeclaredField("userService");
                f.setAccessible(true);
                f.set(action, new SimpleUserService());
            } catch (ReflectiveOperationException e) {
                throw new ServletException("failed to inject userService: " + e.getMessage(), e);
            }
        }
        String result;
        try {
            result = proxy.execute();
        } catch (Exception e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            throw new ServletException("Action execution failed: " + cause.getMessage(), cause);
        }
        this.actionInvocation = proxy.getInvocation();
        return result;
    }

    @Test
    public void testUserList() throws Exception {
        String result = executeAction("/user-list.action");
        assertEquals("success", result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertNotNull(action.getUsers());
    }

    @Test
    public void testUserShow() throws Exception {
        String result = executeAction("/user-show.action");
        assertEquals("success", result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertNotNull(action.getOneUser());
    }
}
