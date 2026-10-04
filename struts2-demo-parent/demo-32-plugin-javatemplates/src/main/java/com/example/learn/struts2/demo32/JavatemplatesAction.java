package com.example.learn.struts2.demo32;

import org.apache.struts2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * demo-32: Javatemplates / plainText 插件演示。
 * <p>
 * 本 demo 强调"无 JSP"——直接在 struts.xml 里以 result type="javanano" 或 "plainText"
 * 把模板字符串写到 HttpServletResponse。前端无需任何视图文件（*.jsp / *.html）。
 */
public class JavatemplatesAction extends ActionSupport {

    private String message = "Javatemplates 插件演示";
    private List<String> lines = new ArrayList<>();

    @Override
    public String execute() {
        lines.add("第一行：Javatemplates = .java.html 模板 + 简化 HTML 输出 API");
        lines.add("第二行：javatemplates result 找到模板，编译为 Java 类");
        lines.add("第三行：执行该类的 render() 方法，输出 HTML 字符串");
        lines.add("第四行：本 demo 用 javanano / plainText 直接渲染字符串");
        return SUCCESS;
    }

    /**
     * 作为 result type="javanano" / "plainText" 的内容源。
     * 模板字符串（可直接调用 Action getter 做简单拼接）。
     */
    public String getTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html><head><meta charset='UTF-8'><title>").append(message).append("</title></head>\n");
        sb.append("<body>\n");
        sb.append("  <h1>").append(message).append("</h1>\n");
        sb.append("  <p>纯 Java / 纯字符串模板，零 JSP。</p>\n");
        sb.append("  <ul>\n");
        for (String l : lines) {
            sb.append("    <li>").append(l).append("</li>\n");
        }
        sb.append("  </ul>\n");
        sb.append("</body></html>");
        return sb.toString();
    }

    public String getMessage() { return message; }
    public List<String> getLines() { return lines; }
}