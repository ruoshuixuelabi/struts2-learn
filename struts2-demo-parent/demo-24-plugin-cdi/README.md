# Demo 24: Plugin - CDI

`struts2-cdi-plugin` 演示：Action 通过 `@Inject` 注入服务，由 CDI 容器（JBoss Weld）管理。

## 启动

```bash
mvn -pl demo-24-plugin-cdi jetty:run
```

访问：http://localhost:8080/demo-24-plugin-cdi/user.action

## 文件清单

- `UserAction.java`：用 `@Inject` 注入 `UserService`
- `UserService.java`：CDI Bean（`@RequestScoped`），内部再用 `@Inject` 注入 `UserRepository`
- `UserRepository.java`：CDI Bean，模拟数据层
- `model/User.java`：数据模型
- `struts.xml`：常量 `struts.objectFactory=cdi`
- `web.xml`：注册 Struts 过滤器
- `META-INF/beans.xml`：CDI 扫描配置（`bean-discovery-mode="annotated"`）

## 关键配置

```xml
<!-- struts.xml -->
<constant name="struts.objectFactory" value="cdi"/>
```

```xml
<!-- META-INF/beans.xml -->
<beans xmlns="https://jakarta.ee/xml/ns/jakartaee"
       bean-discovery-mode="annotated">
</beans>
```

## 7.x 注意点

- 必须用 `jakarta.enterprise.*`（不是 `javax.enterprise.*`）
- Weld 4.0+ 兼容 Jakarta EE 10
- 在 Tomcat/Jetty 部署需要 `weld-se-core` 嵌入式启动

## 对应文档

参见 `struts2-learn/26-插件-CDI.md`
