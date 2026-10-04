# Demo 14: 验证框架 XML 校验（Struts 7.3.0）

演示 Struts 2.x 经典 **XML 校验**：`<ActionClass>-validation.xml` + 验证失败自动返回 `input` result。

## 启动

```bash
mvn -pl demo-14-validation jetty:run
```

测试 URL：

- http://localhost:8080/demo-14-validation/index.jsp （表单入口）
- 提交空 username 或非法 age → 验证失败 → 跳回表单显示错误
- 提交合法 username(3-20字符) + age(0-150) → success

## 文件清单

- `UserAction.java`：业务 Action，username / age 字段
- `UserAction-validation.xml`：与 Action 同包，XML 校验规则
- `struts.xml`：声明 action，input/success 两个 result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `index.jsp`：表单（input result 目标页）
- `success.jsp`：校验通过后的成功页
- `UserActionTest.java`：JUnit 5 测试覆盖"失败 / 成功"两条路径

## 对应文档

参见 `struts2-learn/16-验证框架（XML+注解）.md`

## 验证触发时序

```
请求到达
   ↓
validation 拦截器
   ├─ 读 <ActionClass>-validation.xml
   ├─ 读注解
   └─ 调用 Action.validate()（如覆写）
   ↓
有错 → return "input"
无错 → 继续业务方法
```

## 7.x 关键点

- XML 文件必须命名为 `<ActionClass>-validation.xml`（注意 Action 前缀）
- DTD：`https://struts.apache.org/dtds/validators-7.0.dtd`
- 失败 result 必须配 `<result name="input">`，否则报"No result defined for input"
- 错误消息可用 `key="user.username.required"` 走 i18n 资源文件