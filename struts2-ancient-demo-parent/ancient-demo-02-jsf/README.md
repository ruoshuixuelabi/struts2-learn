# ancient-demo-02-jsf

**主题**：Struts 2.3.37 JSF 桥接（struts2-jsf-plugin）

JSF 桥接让 Struts 2 Action 可以调用 JSF 1.x 的 `FacesContext`、
访问 JSF ManagedBean，实现 Struts 业务层 + JSF 表现层的混合架构。

## 启动

```bash
mvn -pl ancient-demo-02-jsf -am install -DskipTests

# 部署到 Tomcat 8.5+，访问：
# http://localhost:8080/ancient-demo-02-jsf/jsfBridge.action
```

## 文件清单

- `JsfBridgeAction.java`：Struts 2 Action，调用 JSF ManagedBean
- `MessageBean.java`：JSF 1.x `@ManagedBean(name="msg")` + `@RequestScoped`
- `struts.xml`：继承 `jsf-default` 包；`<result type="jsf">` 走 JSF 渲染
- `web.xml`：Struts 2 过滤器 + FacesServlet（JSF 必需）
- `jsf-result.jsp`：混合 JSF + JSP 视图

## 关键点

- **依赖**：`struts2-jsf-plugin` + `javax.faces:jsf-api` + Mojarra 实现
- **包继承**：必须 `extends="jsf-default"`，才能用 `<result type="jsf">`
- **JSF 版本**：JSF 1.x（`javax.faces.bean.*`），不是 2.3+
- **2.5 移除原因**：JSF 1.x 已 EOL；Struts 团队认为 JSF 是 EJB 时代的产物；与 Convention/Annotation 冲突
- **JSF ManagedBean 注入**：Struts 2 无法直接 `@Inject`，需通过 `FacesContext.getCurrentInstance().getELContext()`

## 对应文档

参见 `struts2-learn/远古-Struts2.3历史插件.md`。
