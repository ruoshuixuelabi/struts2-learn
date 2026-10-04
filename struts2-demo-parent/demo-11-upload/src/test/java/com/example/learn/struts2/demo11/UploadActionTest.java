package com.example.learn.struts2.demo11;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testUploadActionWithoutFileReturnsInput() throws Exception {
        // 没传文件：UploadAction 返回 INPUT（addActionError 后）
        String result = executeAction("/upload.action");
        assertEquals("input", result);
    }
}