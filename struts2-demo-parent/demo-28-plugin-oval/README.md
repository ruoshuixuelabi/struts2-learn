# Demo 28: OVal 插件（OVal 验证集成）

演示 Struts 7.3.0 `struts2-oval-plugin` + OVal 1.90：用 `@NotNull` / `@Length` / `@Email` / `@MatchPattern` 等 OVal 注解做字段验证。

> **注意**：OVal 插件自 Struts 6.0 起被官方标记为 deprecated（按 Spec §7.4）。**新项目推荐使用 `struts2-bean-validation-plugin`（JSR-303）**；本 demo 仅用于学习与维护遗留项目。

## 启动

```bash
mvn -pl demo-28-plugin-oval jetty:run
```

访问：
- 表单页：http://localhost:8080/demo-28-plugin-oval/
- 提交 Action：`/oval/save.action`

## 文件清单

- `User.java`：使用 OVal `@NotNull` / `@Length` / `@Email` / `@MatchPattern` 注解
- `UserAction.java`：Action + 嵌套 `@Valid` 用户对象
- `struts.xml`：声明 `save` action + input/success result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `form.jsp`：注册表单（提交后由 OVal 拦截器校验）
- `success.jsp`：校验成功后的视图

## 对应文档

参见 `struts2-learn/30-插件-OVal.md`