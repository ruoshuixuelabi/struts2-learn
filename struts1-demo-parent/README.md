# Struts 1 Demo Parent（Struts 1.3.10）

**Struts 1.x 时代**历史对照，用于理解 ActionForm 模式与 Struts 2 POJO Action 的差异。

- Struts 版本：1.3.10
- JDK：5/6
- Servlet API：javax.servlet 2.5
- Tomcat：7.x / 8.x

## 包含 2 个子模块

- `struts1-demo-01-helloworld`：跑通 Struts 1 最简 Action
- `struts1-demo-02-actionform`：演示 ActionForm 模式（与 Struts 2 POJO Action 对比）

## 注意事项

- Struts 1 时代配置文件是 `struts-config.xml`（不是 `struts.xml`）
- Action 类必须继承 `org.apache.struts.action.Action`
- 表单数据通过 `ActionForm` 子类传递（不是 POJO）
- 此父项目独立构建