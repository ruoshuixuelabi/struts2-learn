package com.example.learn.struts2.demo15;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GreetActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    /**
     * 成功路径：传入 name=Alice，应返回 SUCCESS，且 message 已正确拼接。
     */
    @Test
    public void testGreetSuccess() throws Exception {
        request.setParameter("name", "Alice");

        String result = executeAction("/greet.action");

        assertEquals("success", result);
        GreetAction action = (GreetAction) actionInvocation.getAction();
        assertEquals("Hello, Alice! (Struts 7.3.0)", action.getMessage());
    }

    /**
     * 失败路径：name 为空，应返回 input，且记录字段错误。
     */
    @Test
    public void testGreetMissingName() throws Exception {
        request.setParameter("name", "");

        String result = executeAction("/greet.action");

        assertEquals("input", result);
        GreetAction action = (GreetAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors(), "应当存在字段错误");
        assertNotNull(action.getFieldErrors().get("name"));
    }

    /**
     * 边界值：name 全空格（trim 后为空），也应进入 input 分支。
     */
    @Test
    public void testGreetBlankName() throws Exception {
        request.setParameter("name", "   ");

        String result = executeAction("/greet.action");

        assertEquals("input", result);
        GreetAction action = (GreetAction) actionInvocation.getAction();
        assertTrue(action.hasFieldErrors());
    }

    /**
     * 嵌套 OGNL 路径演示：通过 user.name 这种带点的 key 模拟表单对象。
     * （GreetAction 未消费 user.name，仅演示 setParameter 用法）
     */
    @Test
    public void testSetParameterWithDots() throws Exception {
        request.setParameter("user.name", "Bob");
        request.setParameter("name", "Bob");

        String result = executeAction("/greet.action");

        assertEquals("success", result);
        GreetAction action = (GreetAction) actionInvocation.getAction();
        assertEquals("Hello, Bob! (Struts 7.3.0)", action.getMessage());
    }
}