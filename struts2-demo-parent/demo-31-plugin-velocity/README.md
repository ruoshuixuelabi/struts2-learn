# Demo 31: Velocity 插件（VTL 模板引擎）

演示 Struts 7.3.0 `struts2-velocity-plugin` + Apache Velocity 2.3：用 `.vm` 文件 + VTL 语法（`#set` / `#if` / `#foreach`）作为视图层。

> **注意**：Apache Velocity 项目本身已 **进入 Apache Attic**（2018 年退役）。**新项目推荐使用 FreeMarker 或 Thymeleaf**；本 demo 用于学习与维护遗留项目（典型场景：邮件模板 / 代码生成器）。

## 启动

```bash
mvn -pl demo-31-plugin-velocity jetty:run
```

访问：
- 用户列表（HTML 视图）：http://localhost:8080/demo-31-plugin-velocity/user-list.action
- 邮件预览（纯文本视图）：http://localhost:8080/demo-31-plugin-velocity/email-preview.action

## 文件清单

- `UserListAction.java` / `EmailPreviewAction.java`：2 个 Action
- `struts.xml`：继承 `velocity-default`，声明 `velocity` result type + action 映射
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `WEB-INF/vm/hello.vm`：用户列表模板（`#foreach` / `#if`）
- `WEB-INF/vm/email-template.vm`：邮件模板（`#set` / `${var}`）

## 对应文档

参见 `struts2-learn/33-插件-Velocity.md`