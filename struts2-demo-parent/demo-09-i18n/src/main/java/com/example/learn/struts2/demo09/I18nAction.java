package com.example.learn.struts2.demo09;

import org.apache.struts2.ActionSupport;

import java.util.Locale;

/**
 * 演示 Action 中使用 getText() 取 i18n 资源。
 *
 * getText() 来自 org.apache.struts2.ActionSupport（7.x 推荐的新包名，
 * 不是 com.opensymphony.xwork2.ActionSupport）。
 */
public class I18nAction extends ActionSupport {

    private String userName = "Alice";
    private String welcomeText;
    private String greetingText;
    private String defaultText;
    private Locale currentLocale;

    @Override
    public String execute() {
        // 1. 普通 key
        welcomeText = getText("welcome");

        // 2. 带参数（占位符 {0}）
        greetingText = getText("hello.user", new String[]{userName});

        // 3. 带默认值（key 不存在时返回默认值）
        defaultText = getText("nonexistent.key", "Default Fallback Text");

        // 4. 当前 Locale（演示 Locale 解析顺序生效）
        currentLocale = getLocale();

        return SUCCESS;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getWelcomeText() { return welcomeText; }
    public String getGreetingText() { return greetingText; }
    public String getDefaultText() { return defaultText; }
    public Locale getCurrentLocale() { return currentLocale; }
}