package com.example.learn.struts2.demo23;

import jakarta.servlet.ServletException;
import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserResourceActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    /**
     * 覆盖父类 executeAction：手动解析 "action!method" 形式 URI，
     * 通过 ActionProxyFactory.createActionProxy(ns, name, method, ...) 显式传 method。
     * RestfulActionMapper 不支持 "!" prefix，所以默认父类实现会找不到 method。
     */
    @Override
    protected String executeAction(String uri) throws ServletException {
        int bang = uri.indexOf('!');
        String actionUri = bang >= 0 ? uri.substring(0, bang) : uri;
        String method = bang >= 0 ? uri.substring(bang + 1) : null;

        org.apache.struts2.ServletActionContext.setRequest(this.request);
        org.apache.struts2.ServletActionContext.setResponse(this.response);

        this.request.setRequestURI(actionUri);
        org.apache.struts2.dispatcher.mapper.ActionMapping mapping = getActionMapping(this.request);
        org.apache.struts2.ActionProxyFactory factory =
            this.container.getInstance(org.apache.struts2.ActionProxyFactory.class);
        String resolvedMethod = (method != null) ? method : mapping.getMethod();
        org.apache.struts2.ActionProxy proxy =
            factory.createActionProxy(mapping.getNamespace(), mapping.getName(),
                resolvedMethod, java.util.Collections.emptyMap(),
                /*executeResult*/ false, /*cleanupContext*/ true);
        initActionContext(proxy.getInvocation().getInvocationContext());
        org.apache.struts2.ServletActionContext.setServletContext(this.servletContext);
        org.apache.struts2.ServletActionContext.setRequest(this.request);
        org.apache.struts2.ServletActionContext.setResponse(this.response);
        org.apache.struts2.ActionContext.getContext().put("struts.actionMapping", mapping);
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
    public void testIndex() throws Exception {
        String result = executeAction("/users!index");
        assertEquals("success", result);
        UserResourceAction action = (UserResourceAction) actionInvocation.getAction();
        assertNotNull(action.getUsers());
        assertEquals(2, action.getUsers().size());
    }

    @Test
    public void testShow() throws Exception {
        request.setParameter("id", "42");
        String result = executeAction("/users!show");
        assertEquals("success", result);
        UserResourceAction action = (UserResourceAction) actionInvocation.getAction();
        assertEquals(42L, action.getUser().getId());
    }

    @Test
    public void testCreate() throws Exception {
        request.setParameter("user.username", "charlie");
        String result = executeAction("/users!create");
        assertEquals("create", result);
    }

    @Test
    public void testUpdate() throws Exception {
        request.setParameter("id", "1");
        request.setParameter("user.username", "alice2");
        String result = executeAction("/users!update");
        assertEquals("success", result);
    }

    @Test
    public void testDestroy() throws Exception {
        request.setParameter("id", "1");
        String result = executeAction("/users!destroy");
        assertEquals("destroy", result);
    }
}
