# Demo 15: JUnit 单元测试（StrutsJUnit5Test）

演示 Struts 7.x 推荐的单测方案：JUnit 5 + `StrutsJUnit5Test` 基类 + `executeAction()` 模拟请求。

## 启动

```bash
mvn -pl demo-15-junit jetty:run
```

访问：http://localhost:8080/demo-15-junit/greet.action?name=Alice

## 运行测试

```bash
mvn -pl demo-15-junit test
```

## 文件清单

- `GreetAction.java`：业务 Action（接收 `name` 参数，返回问候语）
- `struts.xml`：`greet` action 映射 + `success` / `input` 两个 result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `greet.jsp`：显示 `${message}`
- `form.jsp`：提交 `name` 的表单
- `GreetActionTest.java`：`StrutsJUnit5Test` 单测（成功 / 失败 / 边界三类用例）

## 关键点

- **基类**：`org.apache.struts2.StrutsJUnit5Test`（7.x 推荐；2.x 用 `StrutsTestCase`）
- **`getConfigPath()`**：必须返回 classpath 下 `struts.xml` 的相对路径
- **`request.setParameter()`**：模拟 HTTP 参数（支持 `user.name` 这种 OGNL 嵌套路径）
- **`executeAction("/xxx.action")`**：触发完整拦截器链，返回 result 字符串
- **`actionInvocation.getAction()`**：拿到真实 Action 实例，验证内部状态

## 对应文档

- `struts2-learn/17-单元测试（JUnit+TestNG）.md`
- `struts2-learn/27-插件-JUnit.md`