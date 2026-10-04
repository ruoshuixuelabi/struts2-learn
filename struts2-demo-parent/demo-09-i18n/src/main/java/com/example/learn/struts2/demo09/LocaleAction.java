package com.example.learn.struts2.demo09;

import org.apache.struts2.ActionSupport;

/**
 * 语言切换 Action：
 * - URL: /locale.action?request_locale=zh_CN  （或 en_US）
 * - i18n 拦截器自动从 request 参数读取 request_locale 写入 session
 *   并刷新当前请求的 Locale，本 Action 只需简单 SUCCESS 即可
 * - 通过 redirectAction 跳回 hello 触发页面重新渲染
 */
public class LocaleAction extends ActionSupport {
    @Override
    public String execute() {
        // i18n 拦截器已经设置好 Locale，这里什么都不用做
        return SUCCESS;
    }
}