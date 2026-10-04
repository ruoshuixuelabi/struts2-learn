package com.example.learn.struts2.demo24;

import jakarta.servlet.ServletException;
import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

/**
 * CDI 集成测试。注意：测试环境无 JNDI BeanManager，所以 struts.xml 中
 * struts.objectFactory 强制覆盖为 "struts"，本测试仅验证 Action 流程可执行；
 * @Inject 字段在测试模式下不会被注入——这里用反射注入一个内存 UserService。
 * 生产环境 mvn jetty:run（Weld servlet 容器自动提供 BeanManager 走真 CDI 注入）。
 */
public class UserActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Override
    protected String executeAction(String uri) throws ServletException {
        // 反射注入 userService，模拟 CDI 注入效果
        org.apache.struts2.ServletActionContext.setRequest(this.request);
        org.apache.struts2.ServletActionContext.setResponse(this.response);
        org.apache.struts2.ActionProxy proxy = getActionProxy(uri);
        Object action = proxy.getAction();
        if (action instanceof UserAction) {
            try {
                Field f = UserAction.class.getDeclaredField("userService");
                f.setAccessible(true);
                f.set(action, new InMemoryUserService());
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
    public void testUserAction() throws Exception {
        String result = executeAction("/user.action");
        assertEquals("success", result);
        UserAction action = (UserAction) actionInvocation.getAction();
        assertNotNull(action.getUsers());
    }
}
