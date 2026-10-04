package com.example.learn.struts2.legacy06;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Portlet + Tiles 集成 Action。
 *
 * 在 Portlet 模式下，Struts 的 PortletRequest / PortletResponse 会替代
 * 普通 ServletRequest / ServletResponse；Tiles result 会按 Tiles 定义
 * 把 header / body / footer 三个 Tile 拼装成最终页面。
 */
public class PageAction extends ActionSupport {

    private Page page = new Page();

    /** Render 阶段：用户首次访问 Portlet */
    public String view() {
        page.setTitle("欢迎来到门户首页");
        page.setBody("这是 Struts 2 + Portlet + Tiles 集成示例");
        return SUCCESS;
    }

    /** Action 阶段：用户提交表单后被调用 */
    public String submit() {
        page.setTitle("提交完成");
        page.setBody("您提交的标题=" + page.getTitle() + "，正文=" + page.getBody());
        return SUCCESS;
    }

    public Page getPage() { return page; }
    public void setPage(Page page) { this.page = page; }
}
