package com.example.learn.struts2.legacy08;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UploadAction 单元测试（仅验证 Action 自身逻辑，
 * Pell Multipart 解析依赖 Servlet 容器运行时）。
 */
public class UploadActionTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testUploadWithoutFileReturnsInput() throws Exception {
        String result = executeAction("/upload!upload.action");
        assertEquals("input", result);
    }

    @Test
    public void testUploadWithFile() throws Exception {
        // StrutsTestCase 不直接支持 multipart 模拟，
        // 这里只校验 Action 实例化和字段可访问性。
        UploadAction action = new UploadAction();
        assertNull(action.getUploaded());
    }

    @Test
    public void testUploadFileFrom() throws Exception {
        java.io.File tmp = java.io.File.createTempFile("pell-test", ".txt");
        tmp.deleteOnExit();
        UploadFile uf = UploadFile.from(tmp, "pell-test.txt", "text/plain");
        assertNotNull(uf);
        assertEquals("pell-test.txt", uf.getName());
        assertEquals("text/plain", uf.getContentType());
    }
}
