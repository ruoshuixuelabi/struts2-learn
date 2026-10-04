# struts1-demo-01-helloworld

最简 Struts 1 示例：演示 ActionServlet + ActionForm + struts-config.xml 的最小组合。

## Struts 1 vs Struts 7.3.0 对照表

| 概念 | Struts 1.3.10 | Struts 7.3.0 |
|---|---|---|
| 请求入口 | ActionServlet（Servlet） | FilterDispatcher / StrutsPrepareAndExecuteFilter（Filter） |
| Action 基类 | `extends Action` | POJO（可选 `implements Action`） |
| Form 数据 | `extends ActionForm` | Action 属性 + Getter/Setter |
| 配置 XML | `struts-config.xml` | `struts.xml` |
| 路径映射 | `/hello.do` | `/hello.action` |
| 转发 | `mapping.findForward("name")` | `<result name="name">...</result>` |
| 注解支持 | 不支持 | 支持（`@Action`, `@Namespace` 等） |
| Servlet API | javax.servlet 2.5 | jakarta.servlet 6 |
| 生命周期 | 2000-2013 | 2006-至今（7.x 是 2026 GA） |
