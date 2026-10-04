package com.example.learn.struts2.demo33;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.struts2.ActionInvocation;
import org.apache.struts2.result.StrutsResultSupport;

/**
 * demo-33 专用：什么都不做的 result，跳过 Struts jasper result 的 JRLoader 路径，
 * 让 DynamicReportAction 可以在测试里只校验"execute 返回 success + jasperPrint 已填充"，
 * 不强制走 jasper result 的渲染路径（生产场景可换回 jasper result + 预编译 .jasper 文件）。
 */
public class NoOpResult extends StrutsResultSupport {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        HttpServletResponse response = invocation.getInvocationContext().getServletResponse();
        if (response != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain;charset=UTF-8");
            response.setContentLength(0);
        }
    }
}