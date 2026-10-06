# legacy-demo-07-dwr

**主题**：Struts 2.5.30 DWR 集成（struts2-dwr-plugin）

DWR（Direct Web Remoting）把 Java 类的方法直接暴露为浏览器可调用的
JavaScript 函数。`struts2-dwr-plugin` 让 Struts Action 既能渲染 JSP，
又能通过 DWR 暴露给前端 JS 调用。

## 启动

```bash
mvn -pl legacy-demo-07-dwr -am install -DskipTests

# 部署到 Tomcat 9.x，访问：
# http://localhost:8080/legacy-demo-07-dwr/greet.action
# http://localhost:8080/legacy-demo-07-dwr/dwr/interface/Greeter.js
# http://localhost:8080/legacy-demo-07-dwr/dwr/interface/GreeterService.js
```

## 文件清单

- `GreetAction.java`：Action，持有 `GreetService` 引用
- `GreetService.java`：纯 POJO，含 `greet(String)` 和 `add(int,int)` 两个方法
- `struts.xml`：包继承 `struts-default,dwr-default`；保留 success result 走 JSP
- `dwr.xml`：DWR 配置，定义两个 `<create>`：Greeter（来自 Action）+ GreeterService（new）
- `web.xml`：Struts 2 过滤器 + DWR servlet
- `greet.jsp`：前端 JSP，含 DWR 自动生成的 JS 调用代码

## 关键点

- **依赖**：`struts2-dwr-plugin-2.5.30.jar` + `org.directwebremoting:dwr`
- **DWR creator**：`<create creator="struts2">` 表示从 Struts 容器取 Action；
  `<create creator="new">` 表示直接 `new` 一个 service
- **方法白名单**：`<include method="greet"/>` 控制暴露给前端的方法
- **前端调用**：
  ```javascript
  GreeterService.greet("Alice", function(data) { alert(data); });
  ```
- **7.x 移除原因**：DWR 项目已停维护；现代方案用 `@RestController` + axios

## 对应文档

参见 `struts2-learn/历史插件-DWR.md`（如未实现）。
