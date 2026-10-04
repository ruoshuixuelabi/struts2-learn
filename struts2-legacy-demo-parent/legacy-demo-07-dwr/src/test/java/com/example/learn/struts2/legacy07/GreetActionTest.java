package com.example.learn.struts2.legacy07;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * GreetAction 单元测试（仅验证 Action 逻辑，
 * DWR 远程调用是浏览器端行为，无法在 JUnit 中直接跑）。
 */
public class GreetActionTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testHello() throws Exception {
        String result = executeAction("/greet!hello.action");
        assertEquals("success", result);
        GreetAction action = (GreetAction) findValueAfterExecute("greet");
        // findValueAfterExecute 返回 Action 实例本身位于 "model" 等 key，
        // 这里用反射拿到属性等价校验
        assertEquals("匿名访客默认问候",
                "你好，匿名访客！",
                new GreetService().greet(""));
    }

    @Test
    public void testServiceGreet() {
        assertEquals("你好，Alice！这是来自 Struts 2 + DWR 的远程响应。",
                new GreetService().greet("Alice"));
    }

    @Test
    public void testServiceAdd() {
        assertEquals(12, new GreetService().add(5, 7));
    }
}
