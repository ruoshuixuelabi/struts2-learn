# Demo 18: 验证框架（注解方式）

演示 Struts 7.x 推荐的注解式校验方案：
- **JSR-303 注解**（`@NotNull` / `@Size` / `@Email` / `@Min` / `@Max`）—— 通过 Bean Validation 插件启用
- **Struts 注解**（`@RequiredStringValidator` / `@StringLengthFieldValidator` / `@IntRangeFieldValidator`）—— Struts 内置验证器

## 启动

```bash
mvn -pl demo-18-validation-annotation jetty:run
```

访问：http://localhost:8080/demo-18-validation-annotation/user-save.action

## 文件清单

- `UserAction.java`：使用 JSR-303 + Struts 注解混合校验
- `struts.xml`：`user-save` 映射 + `success` / `input` 两个 result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `user-form.jsp`：表单 + `<s:fielderror/>` 显示错误

## 关键点

- **依赖**：`struts2-bean-validation-plugin`（启用 `@NotNull` 等 JSR-303 注解）
- **`@NotNull` / `@Size` / `@Email`**：jakarta.validation.constraints.* 标准
- **`@RequiredStringValidator` / `@StringLengthFieldValidator` / `@IntRangeFieldValidator`**：org.apache.struts2.validator.annotations.* Struts 专用
- **失败 → `input`**：validation 拦截器发现错误自动返回 `input` result

## 对应文档

- `struts2-learn/16-验证框架（XML+注解）.md`（注解部分）
- `struts2-learn/29-插件-Bean-Validation.md`