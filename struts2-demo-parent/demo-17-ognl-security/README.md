# Demo 17: OGNL 安全加固（@StrutsParameter + allowlist）

演示 Struts 7.x 推荐的 OGNL 安全做法：
- `@StrutsParameter` 显式声明可注入字段
- OGNL allowlist 限制可访问类/包
- 拦截器过滤危险请求

## 启动

```bash
mvn -pl demo-17-ognl-security jetty:run
```

正常访问：http://localhost:8080/demo-17-ognl-security/profile.action?username=alice

危险请求演示：http://localhost:8080/demo-17-ognl-security/profile.action?username=alice&cmd=%40java.lang.Runtime%40getRuntime().exec('id')
（应被拦截器拦截，返回 hacker）

## 文件清单

- `ProfileAction.java`：用 `@StrutsParameter` 显式声明字段
- `OgnlSecurityInterceptor.java`：自定义拦截器，过滤 `java.lang.Runtime` / `#_memberAccess` 等危险串
- `struts.xml`：注册拦截器 + OGNL allowlist 配置
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `profile.jsp`：用 Struts 标签输出 `${username}`
- `hacker.jsp`：被拦截后的友好提示页

## 关键点

- **`@StrutsParameter`**：字段必须显式声明才被 params 拦截器注入
- **`required = true`**：未传参数时抛 `StrutsParameterValidationException`
- **`struts.xwork.allowed.package.names`**：限制 OGNL 可访问的包
- **拦截器**：字符串黑名单作为最后一道防线

## 对应文档

- `struts2-learn/18-Security安全加固.md`（OGNL 部分）
- `struts2-learn/08-OGNL表达式.md`