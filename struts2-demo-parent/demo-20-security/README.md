# Demo 20: 全套安全加固（Security Hardening）

演示 Struts 7.x 推荐的全套安全配置：
1. **`strictMethodInvocation=true`**：禁止 URL 调用未声明业务方法
2. **`@StrutsParameter` 显式声明字段**
3. **OGNL allowlist**：限制可访问类/包
4. **`ognl.excludedClasses`**：禁掉 `Runtime` / `ProcessBuilder` / `System`
5. **`devMode=false`**：生产模式（不暴露堆栈）
6. **自定义 OGNL 黑名单拦截器**（最后一道防线）

## 启动

```bash
mvn -pl demo-20-security jetty:run
```

访问：http://localhost:8080/demo-20-security/secure-action.action

## 文件清单

- `SecureAction.java`：`@StrutsParameter` 声明 + OGNL 安全使用
- `OgnlHardeningInterceptor.java`：黑名单拦截器
- `struts.xml`：完整安全配置（4 道防线）
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `secure-result.jsp`：安全输出
- `hacker.jsp`：被拦截的提示页
- `SecureActionTest.java`：StrutsJUnit5Test 验证

## 关键点

- **3 层防御**：
  1. **`@StrutsParameter`**：严格参数注入
  2. **OGNL allowlist + excludedClasses**：限制 OGNL 能力
  3. **拦截器黑名单**：拦截已知危险 payload
- **`strictMethodInvocation=true`**：URL 调用未声明方法（如 `secureAction!hack()`）直接 404
- **`devMode=false`**：生产配置
- **历史漏洞**：S2-045 / S2-048 / S2-052 / S2-053 / S2-061 全部绕过困难

## 对应文档

- `struts2-learn/18-Security安全加固.md`