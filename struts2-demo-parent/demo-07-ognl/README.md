# Demo 07: OGNL 表达式

演示 Struts 7.3.0 中 OGNL 表达式的常见用法：
- 访问 Action 属性（`message`）
- 访问 ContextMap（`#session.user` / `#parameters.foo` / `#request.foo`）
- 集合过滤（`users.{?age > 18}`）
- 集合投影（`users.{name}`）
- 方法调用（`message.toUpperCase()`）
- 数学运算（`price * quantity`）
- **静态方法（受限）**：7.x 默认禁用，`struts.xml` 中显式启用 `struts.ognl.allowStaticMethodAccess=true` 演示 `@java.lang.Math@PI`

## 启动

```bash
mvn -pl demo-07-ognl jetty:run
```

访问：http://localhost:8080/demo-07-ognl/ognl.action

## 文件清单

- `OgnlAction.java`：准备 `users` 列表 / `price` / `quantity` / `message`
- `User.java`：POJO（name / age）
- `struts.xml`：OGNL 安全常量（`expressionMaxLength` / `allowStaticMethodAccess`）+ action
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `ognl.jsp`：使用 OGNL 过滤、投影、方法调用、静态字段

## 对应文档

参见 `struts2-learn/08-OGNL表达式.md`
