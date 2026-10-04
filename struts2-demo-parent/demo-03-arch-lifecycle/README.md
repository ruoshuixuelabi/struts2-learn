# Demo 03: 核心架构与请求生命周期

演示 Struts 7.3.0 完整请求生命周期：**Filter 入口 → ActionProxy → 自定义拦截器栈 → Action → Result**。

本 demo 串联两个自定义拦截器 `LifecycleLoggerInterceptor`（打印前后日志）和 `LifecycleTimerInterceptor`（计时），让你在控制台看到完整的"洋葱模型"调用链。

## 启动

```bash
mvn -pl demo-03-arch-lifecycle jetty:run
```

访问：http://localhost:8080/demo-03-arch-lifecycle/lifecycle.action

控制台将看到类似输出：

```
[LOGGER] >>> LifecycleAction.execute() 开始
[TIMER]  >>> LifecycleAction.execute() 开始
[LOGGER] 进入 Action 业务逻辑...
[TIMER]  <<< 耗时 = X ms
[LOGGER] <<< result = success
```

## 文件清单

- `LifecycleAction.java`：模拟业务逻辑的 Action
- `LifecycleLoggerInterceptor.java`：前置/后置日志拦截器（实现 `Interceptor`）
- `LifecycleTimerInterceptor.java`：计时拦截器（继承 `AbstractInterceptor`）
- `struts.xml`：自定义拦截器栈 `lifecycleStack`（log → timer → defaultStack）
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `lifecycle.jsp`：使用 `<s:property>` 显示消息

## 对应文档

参见 `struts2-learn/03-核心架构与请求生命周期.md`
