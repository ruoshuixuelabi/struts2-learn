# legacy-demo-02-embedded-jsp

**主题**：Struts 2.5.30 嵌入式 JSP 引擎（struts2-embedded-jsp-plugin）

内置 Tomcat Jasper 引擎，无需 Servlet 容器参与 JSP 编译，
适合把 JSP 视图打包进独立 JAR（脱离外部容器运行的 CLI / 桌面 / 微服务场景）。

## 启动

```bash
mvn -pl legacy-demo-02-embedded-jsp -am install -DskipTests

# 部署到 Tomcat 9.x，访问：
# http://localhost:8080/legacy-demo-02-hello.action
```

## 文件清单

- `HelloAction.java`：Action，根据 `name` 返回不同 greeting
- `struts.xml`：注册 `<result-type name="embeddedJsp">`，action 使用 `type="embeddedJsp"`
- `web.xml`：Struts 2 过滤器
- `hello.jsp`：常规 JSP（用 `${greeting}` EL 渲染）

## 关键点

- **依赖**：`struts2-embedded-jsp-plugin-2.5.30.jar`（自带 Jasper）
- **Result 类型**：`org.apache.struts2.result.EmbeddedJspResult`
- **使用方式**：
  ```xml
  <result-types>
    <result-type name="embeddedJsp"
                 class="org.apache.struts2.result.EmbeddedJspResult"/>
  </result-types>
  <action name="hello" class="...">
    <result name="success" type="embeddedJsp">/hello.jsp</result>
  </action>
  ```
- **6.0 弃用原因**：内置 Jasper 与 Tomcat/Jetty 自带引擎重复，维护成本高；
  现代方案已转向 FreeMarker / Thymeleaf 模板
- **适用场景**：嵌入式 Struts 2 应用 + 模板需要热替换（不用重启容器）
- **限制**：仅支持 JSP 2.x，不支持 JSP 3.0+；且 EL 表达式必须用 `${}` 不支持 `#{}`

## 对应文档

参见 `struts2-learn/历史插件-EmbeddedJSP.md`（如未实现）。
