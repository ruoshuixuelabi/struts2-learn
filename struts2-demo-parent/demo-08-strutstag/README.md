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
# 问题
好问题——这正是把 `@StrutsParameter` 从"照方抓药"升级到"理解设计"的关键。先给结论，再讲原理。

## 结论速查

| 场景 | 注解位置 | 例子 |
|---|---|---|
| 表单提交，往 Action 里**灌数据**（写方向） | **setter** | `@StrutsParameter` 标在 `setUser()` |
| Action 准备数据给页面**展示**，返回的是自定义类（读方向） | **getter** | `@StrutsParameter` 标在 `getUser()` |
| 属性**双向使用**（既提交又回显，如表单 bean） | **两个都标** | demo-08 的 `user` 就是这个情况 |

## 原理：这个注解实际上管两件事

**① 参数注入授权（StrutsParameterAuthorizer 检查）**

请求参数进 Action 时，authorizer 要在 Action 上找"叫这个名字的成员"的注解。从你这两天的日志能实证它两个都会查：

- demo-02：`Parameter injection for method [setProductName] rejected` —— 查的是 setter
- demo-08：`Parameter injection for method [getUser] rejected` —— 查的是 getter

所以对双向属性，**只标一个存在被查另一个的风险**——demo-08 就是只标 setter 时被 getter 的检查挑出来拒了。两头都标，哪条路来检查都有注解接着。

**② 返回类型自动进白名单（OGNL 读方向）**

Struts 7 有个官方机制：**被 `@StrutsParameter` 标注的 getter，其返回类型会自动加入 OGNL 白名单**。所以当你要展示一个自定义 DTO 时，标 getter 一举两得：读权限 + 返回类白名单，可以少写一行 `struts.allowlist.classes`。

## 对照你的 demo-08 复盘

- `user`：既接收表单又回显 → getter + setter 都标 `depth=1` ✅
- `cities`：**什么都没标也正常工作**——因为读 Action 自身属性根本不需要注解（Action 类注册在 struts.xml 就整体在白名单里），而它的返回类型 `List<String>` 都是 JDK 核心类，本来就在白名单。注解 getter 在这纯属多余

## 一个值得记住的推论

**标在 setter 上的注解只管"进"，标在 getter 上的注解兼管"出"（返回类白名单）。** 所以实际项目里的简洁策略：

- 纯表单接收 → 只标 setter
- 纯展示数据且返回自定义类 → 只标 getter（顺便白名单）
- 表单 bean / 双向 → 都标，别赌 authorizer 先查哪个

下次再看到 `Parameter injection for method [xxx] rejected`，先看日志里点名的方法名——它告诉你这次检查走的是哪条路径，缺注解的就补哪个。