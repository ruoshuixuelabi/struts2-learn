# Demo 09: 国际化 i18n 与本地化（Struts 7.3.0）

演示 Struts 2.x 的 i18n 机制：全局资源文件 + 中英文切换 + Action 中 getText() + JSP 中 `<s:text>`。

## 启动

```bash
mvn -pl demo-09-i18n jetty:run
```

访问：http://localhost:8080/demo-09-i18n/hello.action

页面右上角有"中文 / English"切换链接，点击通过 `request_locale` 参数触发 i18n 拦截器重新解析 Locale。

## 文件清单

- `I18nAction.java`：继承 `ActionSupport`，使用 `getText()` 取值
- `LocaleAction.java`：仅用于响应 `request_locale` 参数（i18n 拦截器自动处理 Locale 切换）
- `struts.xml`：声明 `struts.custom.i18n.resources=globalMessages` + 两个 action
- `globalMessages.properties` / `globalMessages_zh_CN.properties` / `globalMessages_en_US.properties`：中英文资源
- `package.properties` / `package_zh_CN.properties`：Action 同包资源（演示包级查找）
- `hello.jsp`：使用 `<s:text>` + `<s:param>` + 切换链接
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`

## 对应文档

参见 `struts2-learn/10-国际化i18n与本地化.md`

## 验证 Locale 解析顺序

i18n 拦截器按以下顺序确定 Locale：

1. request 参数 `request_locale`
2. session 属性 `WW_TRANS_I18N_LOCALE`
3. Cookie `WW_TRANS_I18N_LOCALE`
4. HTTP Header `Accept-Language`
5. 默认 Locale（JVM）