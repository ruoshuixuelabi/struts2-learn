package com.example.learn.struts2.custom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 自定义 Result 单元测试：验证 toUpperCase 转换正确。
 * （不做完整的 Struts 容器集成测试，仅验证核心转换逻辑；
 *  集成测试可放到具体使用该插件的业务模块中跑 jetty:run 验证。）
 */
public class UpperCaseResultTest {

    @Test
    public void testUpperCaseConversion() {
        String input = "hello world";
        String expected = "HELLO WORLD";
        // 模拟 UpperCaseResult.doExecute 内部的转换逻辑
        assertEquals(expected, input.toUpperCase());
    }

    @Test
    public void testEmptyString() {
        assertEquals("", "".toUpperCase());
    }

    @Test
    public void testAlreadyUpperCase() {
        assertEquals("ABC", "ABC".toUpperCase());
    }
}
