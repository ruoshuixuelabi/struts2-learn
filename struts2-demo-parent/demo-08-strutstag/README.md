# Demo 08: Struts 标签库

演示 Struts 7.3.0 常用 UI 标签：`<s:form>` `<s:textfield>` `<s:password>` `<s:radio>` `<s:checkbox>` `<s:select>` `<s:textarea>` `<s:submit>` `<s:reset>`，以及配套的 `<s:actionerror>` `<s:actionmessage>` `<s:fielderror>`。

输入页 `input.jsp` → 提交到 `user-save.action` → 显示结果页 `result.jsp`（自动回显用户输入）。

## 启动

```bash
mvn -pl demo-08-strutstag jetty:run
```

访问：http://localhost:8080/demo-08-strutstag/input.action

## 文件清单

- `UserFormAction.java`：包含 input / save 两个方法；`User` POJO 接收表单字段
- `User.java`：POJO（name / password / age / gender / vip / city / bio）
- `struts.xml`：注册 input / save action
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `input.jsp`：表单页（Struts 标签）
- `result.jsp`：结果页（自动回显 + 显示消息）

## 对应文档

参见 `struts2-learn/09-Struts标签库.md`
