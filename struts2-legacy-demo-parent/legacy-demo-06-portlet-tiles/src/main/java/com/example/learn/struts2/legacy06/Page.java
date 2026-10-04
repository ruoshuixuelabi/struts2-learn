package com.example.learn.struts2.legacy06;

/**
 * Portlet 页面片段模型。Portlet 容器（IBM WebSphere Portal / Liferay / Pluto）
 * 会按 render / action / resource 三阶段调用 Struts Action；Tiles 负责把这些
 * Action 的 JSP 片段拼装成完整门户页。
 *
 * 本类作为 Action 与 Tile 模板之间传递数据的载体。
 */
public class Page {
    private String title = "默认门户标题";
    private String body = "默认门户正文";

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
