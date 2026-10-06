package com.example.learn.struts2.demo21;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ConventionAction 单元测试。
 *
 * <p>本 demo 走"零 XML"路线（仅靠注解 + struts.properties），因此不适合
 * 用 StrutsJUnit5Test（它默认会去找 struts.xml）。这里只测 Action 本身
 * 的业务逻辑——完整 URL 路由验证靠 jetty:run 跑集成。</p>
 */
public class ConventionActionTest {

    @Test
    public void testExecuteReturnsSuccess() {
        ConventionAction action = new ConventionAction();
        String result = action.execute();
        assertEquals("success", result);
    }

    @Test
    public void testMessageNotNull() {
        ConventionAction action = new ConventionAction();
        action.execute();
        assertNotNull(action.getMessage());
        assertFalse(action.getMessage().isEmpty());
    }
}
