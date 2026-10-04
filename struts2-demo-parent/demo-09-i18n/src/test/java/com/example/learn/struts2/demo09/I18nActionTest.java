package com.example.learn.struts2.demo09;

import org.apache.struts2.ActionContext;
import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class I18nActionTest extends StrutsJUnit5Test {

    private static final Locale ORIGINAL_DEFAULT_LOCALE = Locale.getDefault();

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @BeforeEach
    public void forceEnglishLocale() {
        // 强制 JVM 默认 locale 为英文——这样 ResourceBundle.getBundle 会优先选
        // globalMessages_en_US.properties（Struts 执行时基于 Locale.getDefault() 解析
        // resource bundle，不依赖 request locale）。这是唯一能在 StrutsJUnit4TestCase
        // 下生效的方案，因为 executeAction 内部会重新初始化 ActionContext，覆盖前面的设置。
        Locale.setDefault(Locale.ENGLISH);
        ActionContext.getContext().withLocale(Locale.ENGLISH).bind();
        request.setPreferredLocales(java.util.Collections.singletonList(Locale.ENGLISH));
    }

    @AfterEach
    public void restoreDefaultLocale() {
        Locale.setDefault(ORIGINAL_DEFAULT_LOCALE);
    }

    @Test
    public void testHelloActionDefaultLocale() throws Exception {
        String result = executeAction("/hello.action");
        assertEquals("success", result);
        I18nAction action = (I18nAction) actionInvocation.getAction();
        assertEquals("Welcome", action.getWelcomeText());
        assertEquals("Hello, Alice!", action.getGreetingText());
    }
}