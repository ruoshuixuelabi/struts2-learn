# Demo 04: 自定义拦截器（基础）

演示 Struts 7.3.0 自定义拦截器的基本写法。一个 Action 被一个自定义 `MyLoggerInterceptor`（继承 `AbstractInterceptor`）拦截，在控制台打印"前后"日志。

## 启动

```bash
mvn -pl demo-04-interceptor jetty:run
```

访问：http://localhost:8080/demo-04-interceptor/hello.action

控制台输出示例：

```
>>> HelloAction.execute() 开始
HelloAction 业务逻辑...
<<< HelloAction.execute() 结束，result=success，耗时=Xms
```

## 文件清单

- `HelloAction.java`：最简单的 Action
- `MyLoggerInterceptor.java`：继承 `AbstractInterceptor`，实现前后日志
- `struts.xml`：声明拦截器 + 自定义栈（log → defaultStack）
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `hello.jsp`：使用 `${message}` 显示

## 对应文档

参见 `struts2-learn/06-拦截器Interceptor.md`
