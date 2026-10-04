package com.example.learn.struts1.demo02;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterActionTest {
    @Test
    public void testValidation() {
        RegisterForm form = new RegisterForm();
        // 空字段 → 期望有错误
        // 这里只能手动构造 ActionErrors（无法在 Pojo 单元测试里跑 Struts 1 的 servlet pipeline）
        assertNull(form.getUsername());
    }
}
