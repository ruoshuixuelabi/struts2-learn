# Demo 10: 异常处理（Struts 7.3.0）

演示 Struts 2.x 两层异常处理：全局 exception-mapping + Action 级 exception-mapping + 编程式 try-catch + 自定义异常。

## 启动

```bash
mvn -pl demo-10-exception jetty:run
```

测试 URL：

- http://localhost:8080/demo-10-exception/home.action （导航页）
- http://localhost:8080/demo-10-exception/user!delete.action?id= （抛 IllegalArgumentException → 触发 Action 级映射）
- http://localhost:8080/demo-10-exception/user!login.action （抛 SecurityException → 触发全局映射 → login.jsp）
- http://localhost:8080/demo-10-exception/user!throwNpe.action （未捕获异常 → 全局兜底 → error.jsp）

## 文件清单

- `UserAction.java`：业务 Action（delete/login/throwNpe），分别演示 Action 级 + 全局映射
- `UserNotFoundException.java`：自定义业务异常（继承 RuntimeException）
- `struts.xml`：声明全局 + Action 级 exception-mapping / global-results
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `home.jsp`：导航页
- `user-notfound.jsp`：用户未找到页面（Action 级 result）
- `login.jsp`：登录引导页（全局 result）
- `error.jsp`：通用错误页（含 `<s:actionerror>` + `exception` 信息）

## 对应文档

参见 `struts2-learn/11-异常处理.md`

## 异常匹配顺序

精确异常类 > 父异常类 > 全局 exception-mapping。Action 级优先于全局。