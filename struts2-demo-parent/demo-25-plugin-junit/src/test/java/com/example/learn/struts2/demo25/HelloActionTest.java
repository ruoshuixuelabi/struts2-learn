package com.example.learn.struts2.demo25;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 基于 StrutsJUnit5Test（JUnit 5 友好包装）的 Struts Action 单元测试。
 *
 * 这里使用 JUnit 5 + StrutsJUnit5Test 而不是官方 StrutsJUnit4TestCase（JUnit 4），
 * 因为 StrutsJUnit5Test.executeAction(URI) 返回 ActionProxy 执行的 result code（"success" 等），
 * 而 StrutsJUnit4TestCase.executeAction(URI) 返回 response body（测试环境无 JSP 渲染，body 为空）。
 *
 * 真实业务里 struts2-junit-plugin + StrutsJUnit4TestCase 也可以用，但断言需改成
 * 验证 Action 状态字段（getMessage() 等）而不是 result code。
 */
public class HelloActionTest extends StrutsJUnit5Test<HelloAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testHelloWithParam() throws Exception {
        request.setParameter("user.name", "Alice");

        String result = executeAction("/hello.action");

        assertEquals("success", result);
        HelloAction action = getAction();
        assertEquals("Alice", action.getUser().getName());
        assertEquals("Hello, Alice!", action.getMessage());
    }

    @Test
    public void testHelloDefault() throws Exception {
        String result = executeAction("/hello.action");

        assertEquals("success", result);
        HelloAction action = getAction();
        assertEquals(
            "Hello, Struts 7.3.0 (JUnit 5)!",
            action.getMessage()
        );
    }
}
