# Demo 01: Hello World（Struts 7.3.0）

最简 Struts 7.3.0 demo：1 个 Action + 1 个 JSP + 1 个 struts.xml。

## 启动

```bash
mvn -pl demo-01-helloworld jetty:run
```

访问：http://localhost:8080/demo-01-helloworld/hello.action

## 文件清单

- `HelloAction.java`：实现 `org.apache.struts2.action.Action` 接口的 POJO
- `struts.xml`：声明 `hello` action 映射
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `hello.jsp`：使用 `${message}` EL 表达式显示

## 对应文档

参见 `struts2-learn/02-Struts7快速上手-HelloWorld.md`