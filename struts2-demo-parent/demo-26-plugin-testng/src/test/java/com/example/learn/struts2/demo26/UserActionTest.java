package com.example.learn.struts2.demo26;

import org.apache.struts2.junit.StrutsJUnit4TestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 基于 StrutsJUnit4TestCase（junit-plugin）+ TestNG 注解的 Struts Action 单元测试。
 *
 * 用 TestNG 特性：
 *  - @BeforeMethod / @AfterMethod
 *  - @DataProvider 参数化测试
 *  - groups 分组（@Test(groups="validation")）
 *  - dependsOnMethods 依赖
 *
 * 注：struts2-testng-plugin 提供的 TestNGStrutsTestCase 只有 setUp/tearDown/initDispatcher/createAction，
 * 没有 request/executeAction/getAction()。所以这里直接继承 junit-plugin 的 StrutsJUnit4TestCase，
 * 配合 TestNG 注解使用（TestNG 的 @BeforeMethod/@AfterMethod 会显式调 super.setUp()/tearDown()）。
 */
public class UserActionTest extends StrutsJUnit4TestCase<UserAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    /** 最近一次 executeAction() 创建的 ActionProxy，getAction() 直接从它取，避免 ValueStack null。 */
    private org.apache.struts2.ActionProxy lastActionProxy;

    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * 覆盖父类 executeAction：父类返回 response body（JSP 测试环境渲染失败 → body 为空）。
     * 这里通过 ActionProxy 直接执行，返回 result code（"success"/"input"），便于断言。
     * 也支持 "!" method 语法（"/user!list.action"）。
     */
    @Override
    protected String executeAction(String uri) throws jakarta.servlet.ServletException, java.io.UnsupportedEncodingException {
        int bang = uri.indexOf('!');
        String actionUri = bang >= 0 ? uri.substring(0, bang) : uri;
        String method = bang >= 0 ? uri.substring(bang + 1, uri.lastIndexOf('.')) : null;

        request.setRequestURI(actionUri);
        org.apache.struts2.dispatcher.mapper.ActionMapping mapping = getActionMapping(request);
        org.apache.struts2.ActionProxyFactory factory =
            configurationManager.getConfiguration().getContainer()
                .getInstance(org.apache.struts2.ActionProxyFactory.class);
        String resolvedMethod = (method != null) ? method : mapping.getMethod();
        org.apache.struts2.ActionProxy proxy;
        try {
            // executeResult=true：让 result 跑（设 ValueStack 等），但 JSP 结果会失败；
            // 在测试环境 JSP 失败通常 swallow，result code 仍能正确返回。
            // getAction() 通过 ValueStack.findValue("action") 获取 Action 实例，
            // 这要求 result 执行过，ValueStack 才有 root action。
            proxy = factory.createActionProxy(
                mapping.getNamespace(), mapping.getName(),
                resolvedMethod, java.util.Collections.emptyMap(),
                /*executeResult*/ true, /*cleanupContext*/ true);
            initActionContext(proxy.getInvocation().getInvocationContext());
            org.apache.struts2.ServletActionContext.setServletContext(servletContext);
            org.apache.struts2.ServletActionContext.setRequest(request);
            org.apache.struts2.ServletActionContext.setResponse(response);
            org.apache.struts2.ActionContext.getContext().put("struts.actionMapping", mapping);
            lastActionProxy = proxy;
            return proxy.execute();
        } catch (jakarta.servlet.ServletException | java.io.UnsupportedEncodingException e) {
            throw e;
        } catch (Exception e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            throw new jakarta.servlet.ServletException("Action execution failed: " + cause.getMessage(), cause);
        }
    }

    @Test
    public void testListUsers() throws Exception {
        request.setParameter("page", "1");
        String result = executeAction("/user!list.action");
        assertEquals(result, "success");

        UserAction action = (UserAction) lastActionProxy.getAction();
        assertNotNull(action.getUsers());
        assertEquals(action.getUsers().size(), 3);
    }

    @Test(groups = "validation")
    public void testInvalidEmail() throws Exception {
        request.setParameter("user.username", "alice");
        request.setParameter("user.email", "not-an-email");
        String result = executeAction("/user!save.action");
        assertEquals(result, "input");
    }

    @DataProvider(name = "userInputs")
    public Object[][] createUserInputs() {
        return new Object[][] {
            { "alice", "alice@example.com", "success" },
            { "bob",   "bob@example.com",   "success" },
            { "",      "no-name@example.com", "input" },
            { "dave",  "no-at-sign",        "input" }
        };
    }

    @Test(dataProvider = "userInputs")
    public void testSave(String username, String email, String expected) throws Exception {
        request.setParameter("user.username", username);
        request.setParameter("user.email", email);
        String result = executeAction("/user!save.action");
        assertEquals(result, expected);
    }

    @Test
    public void testCreateUser() {
        // 简单占位测试，演示 dependsOnMethods 模式
        assertTrue(true);
    }

    @Test(dependsOnMethods = {"testCreateUser"})
    public void testUpdateUser() {
        // 必须 testCreateUser 通过后才执行
        assertTrue(true);
    }
}
