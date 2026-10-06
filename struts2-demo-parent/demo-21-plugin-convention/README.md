# Demo 21: Plugin - Convention（约定插件）

`struts2-convention-plugin` 演示：基于注解 + 命名约定的"零 XML"配置。

## 启动

```bash
mvn -pl demo-21-plugin-convention jetty:run
```

访问：http://localhost:8080/demo-21-plugin-convention/convention/hello.action

## 文件清单

- `pom.xml`：依赖 `struts2-core` + `struts2-convention-plugin` + `struts2-junit-plugin`（test）
- `ConventionAction.java`：用 `@Namespace("/convention")` + `@Action("/convention/hello")` + `@Results` 注解声明 URL 和视图映射，**完全无 struts.xml**
- `ConventionActionTest.java`：纯 JUnit 5 测试，验证 `execute()` 返回 `success`；完整 URL 路由靠 `jetty:run` 跑集成（StrutsJUnit5Test 依赖 struts.xml，与"零 XML"路线冲突）
- `struts.properties`：启用 Convention 插件扫描包 `com.example.learn.struts2.demo21`
- `convention-result.jsp`：注解 `@Result(name="success", location="/convention-result.jsp")` 引用的视图
- `index.html`：欢迎页（含跳转链接）
- `WEB-INF/web.xml`：Jakarta Servlet 6 + StrutsPrepareAndExecuteFilter

## 配置关键点

Convention 插件靠两条规则自动生成 URL 映射：

```java
@Namespace("/convention")        // URL 命名空间
@Action("/convention/hello")     // URL 路径（覆盖默认约定）
@Results({
    @Result(name = "success", location = "/convention-result.jsp")
})
public class ConventionAction { ... }
```

`struts.properties` 必须声明扫描包：

```properties
struts.convention.action.packages = com.example.learn.struts2.demo21
struts.convention.result.path = /WEB-INF/content/
```

## 与 XML 配置的对照

| 维度 | XML（demo-01 ~ 20） | Convention（demo-21） |
|---|---|---|
| URL 映射 | `<action name="...">` | `@Action("/path")` 或类名约定 |
| Result | `<result name="...">` | `@Result(name="...", location="...")` |
| 命名空间 | `<package namespace="...">` | `@Namespace("/...")` |
| 适用规模 | 大型项目（细粒度控制） | 中小型项目（减少样板） |

## 对应文档

参见 `struts2-learn/24-插件-Convention.md`
