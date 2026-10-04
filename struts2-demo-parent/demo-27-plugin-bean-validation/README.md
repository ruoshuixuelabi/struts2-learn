# Demo 27: Bean-Validation 插件（JSR-303 集成）

演示 Struts 7.3.0 `struts2-bean-validation-plugin` + Hibernate Validator + Jakarta Validation API：用 `@NotNull` / `@Size` / `@Email` 等 JSR-303 注解做字段验证。

## 启动

```bash
mvn -pl demo-27-plugin-bean-validation jetty:run
```

访问：
- 表单页：http://localhost:8080/demo-27-plugin-bean-validation/
- 提交 Action：`/bean-validation/save.action`

## 文件清单

- `UserAction.java`：使用 `@NotNull` / `@Size` / `@Email` 注解字段
- `struts.xml`：声明 `save` action + input/success result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `form.jsp`：注册表单（提交后由 Bean Validation 拦截器校验）
- `success.jsp`：校验成功后的视图
- `User.java`：嵌套对象（演示 `@Valid` 级联验证）

## 对应文档

参见 `struts2-learn/29-插件-Bean-Validation.md`