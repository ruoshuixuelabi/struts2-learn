package com.example.learn.struts2.legacy06;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Portlet + Tiles Action 的单元测试（仅验证 Action 自身，
 * Tiles result 的渲染在容器内执行）。
 */
public class PageActionTest extends StrutsTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testView() throws Exception {
        String result = executeAction("/page!view.action");
        assertEquals("success", result);
    }

    @Test
    public void testSubmit() throws Exception {
        request.setParameter("page.title", "测试标题");
        request.setParameter("page.body", "测试正文");
        String result = executeAction("/page!submit.action");
        assertEquals("success", result);
        PageAction action = (PageAction) findValueAfterExecute("page");
        assertNotNull(action);
    }
}
