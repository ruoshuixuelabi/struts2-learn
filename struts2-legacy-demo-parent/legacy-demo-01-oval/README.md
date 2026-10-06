# legacy-demo-01-oval

**主题**：Struts 2.5.30 OVal 校验（struts2-oval-plugin）

OVal 是 Java 生态老牌校验框架（基于注解），比 Struts 2 内置的 `validation.xml`
配置更简洁。`struts2-oval-plugin` 让 Action / Model 字段直接用 `@NotNull` /
`@Length` / `@Email` 注解即可完成服务端校验。

## 启动

```bash
mvn -pl legacy-demo-01-oval -am install -DskipTests

# 部署到 Tomcat 9.x，访问：
# http://localhost:8080/legacy-demo-01-user.action
```

> 注：`struts.xml` 引用的 `/form.jsp` 与 `/success.jsp` 已补全，可直接 `mvn install` 后部署。

## 文件清单

- `User.java`：Model 类，使用 `@NotNull` / `@Length(min=3,max=20)` / `@Email` 三种 OVal 注解
- `UserAction.java`：注入 `User`，提供 `save()` 方法（被 `userAction` action 映射）
- `struts.xml`：声明 `user` action；OVal 校验由 `ovalValidation` 拦截器自动激活
- `web.xml`：Struts 2 过滤器（注意 2.5.x 仍是 `ng.filter.StrutsPrepareAndExecuteFilter`）
- `form.jsp`：用户注册表单（含 `<s:actionerror>` / `<s:fielderror>` 错误回显）
- `success.jsp`：校验通过后的视图，输出用户信息
- `UserActionTest.java`：单元测试，验证校验失败时返回 `input`

## 关键点

- **依赖**：`struts2-oval-plugin-2.5.30.jar` + `net.sf.oval:oval`
- **拦截器**：`ovalValidation`（由插件自动注册到 `defaultStack`）
- **注解位置**：字段级（不是 getter 级）；OVal 反射读字段
- **6.0 弃用原因**：Bean Validation (JSR-303) 已是 Jakarta EE 规范，OVal 边缘化
- **现代替代**：`struts2-bean-validation-plugin`（已在 demo-27 演示）
- **优先级**：OVal 注解 > `validation.xml` 规则（两者并存时 OVal 优先）

## 对应文档

参见 `struts2-learn/历史插件-OVal校验.md`（如未实现，可参考 OVal 官方文档）。
