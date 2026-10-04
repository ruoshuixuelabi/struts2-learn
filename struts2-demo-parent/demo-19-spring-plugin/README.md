# Demo 19: Spring 插件（Spring 接管 Action）

演示 Struts + Spring 6 集成：`struts2-spring-plugin` + `@Autowired` 注入 Service。

## 启动

```bash
mvn -pl demo-19-spring-plugin jetty:run
```

访问：http://localhost:8080/demo-19-spring-plugin/user-list.action

## 文件清单

- `UserService.java` / `UserServiceImpl.java`：业务 Service（`@Service` 标注）
- `UserAction.java`：Action，`@Autowired` 注入 Service
- `struts.xml`：`struts.objectFactory=spring` + autowire=type
- `applicationContext.xml`：Spring 配置（组件扫描 + 显式 Action Bean）
- `web.xml`：`ContextLoaderListener` 加载 Spring + `StrutsPrepareAndExecuteFilter`
- `user-list.jsp`：显示 `${users}`
- `UserActionTest.java`：StrutsJUnit5Test 验证 Service 注入成功

## 关键点

- **依赖**：`struts2-spring-plugin` + `spring-context` + `spring-web`
- **Spring 6.1+**：Jakarta Servlet 兼容（Spring 5 用 `javax.*`，不可用）
- **`struts.objectFactory = spring`**：让 SpringObjectFactory 替代默认 ObjectFactory
- **`scope="prototype"`**：Action 每次请求新建实例（避免线程安全问题）
- **`@Autowired` on Action 字段**：Spring 自动注入 Service

## 对应文档

- `struts2-learn/25-插件-Spring.md`
- `struts2-learn/04-Action详解.md`