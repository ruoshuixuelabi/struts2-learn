# legacy-demo-04-plexus

**主题**：Struts 2.5.30 Plexus IoC 集成（struts2-plexus-plugin）

Plexus 是 Apache 早期 IoC 容器（Maven 内核使用），早于 Spring 流行。
`struts2-plexus-plugin` 把 Struts 的 `ObjectFactory` 切换为 `PlexusObjectFactory`，
让 Action 由 Plexus 容器管理，依赖通过 `META-INF/plexus/components.xml` 注册。

## 启动

```bash
mvn -pl legacy-demo-04-plexus -am install -DskipTests

# 部署到 Tomcat 9.x，访问：
# http://localhost:8080/legacy-demo-04-hello.action?name=Alice
```

## 文件清单

- `HelloAction.java`：Action，`UserService` 由 Plexus 注入（非 Spring `@Autowired`）
- `UserService.java`：Plexus 组件接口
- `UserServiceImpl.java`：Plexus 组件实现，通过 `components.xml` 注册
- `struts.xml`：`<constant name="struts.objectFactory" value="...PlexusObjectFactory"/>`
- `components.xml`：`META-INF/plexus/components.xml` 定义组件 role / implementation
- `web.xml`：Struts 2 过滤器
- `hello.jsp`：展示 `${message}`
- `UserServiceImplTest.java`：单元测试

## 关键点

- **依赖**：`struts2-plexus-plugin-2.5.30.jar` + Plexus Container
- **核心常量**：`struts.objectFactory = org.apache.struts2.plexus.PlexusObjectFactory`
- **组件描述文件**：`META-INF/plexus/components.xml`，类似 Spring 的 `<bean>`
- **注入方式**：setter 注入（与 Spring 一样）
- **6.0 弃用原因**：Plexus 项目停滞（Eclipse Sisu 接管），Spring 已是 IoC 事实标准
- **现代替代**：`struts2-spring-plugin`（已在 demo-19 演示）

## 对应文档

参见 `struts2-learn/历史插件-Plexus.md`（如未实现）。
