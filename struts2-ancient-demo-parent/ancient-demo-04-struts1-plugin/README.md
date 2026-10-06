# ancient-demo-04-struts1-plugin

**主题**：Struts 2.3.37 Struts 1 桥接（struts2-struts1-plugin）

Struts 1 → 2 桥接让老 Struts 1.x 的 `org.apache.struts.action.Action` +
`ActionForm` 可以在 Struts 2 应用里继续运行，避免一次性重写。

## 启动

```bash
mvn -pl ancient-demo-04-struts1-plugin -am install -DskipTests

# 部署到 Tomcat 8.5+，访问：
# http://localhost:8080/ancient-demo-04-struts1-plugin/legacy.action
```

## 文件清单

- `Struts1LegacyAction.java`：继承 `org.apache.struts.action.Action`，是 Struts 1 时代的 Action
- `LegacyForm.java`：继承 `org.apache.struts.action.ActionForm`
- `struts.xml`：包继承 `struts1-default`；用 `<param name="className">` 指向真正的 Struts 1 Action 类
- `web.xml`：注册 Struts 2 过滤器
- `struts1-result.jsp`：渲染 Struts 1 Action 设置的 request attribute

## 关键点

- **依赖**：`struts2-struts1-plugin-2.3.x.jar` + `struts-core-1.3.x.jar`
- **包继承**：必须 `extends="struts1-default"`
- **`<param name="className">`**：告诉 struts1-plugin 这个 action 名对应的真实 Struts 1 Action 类
- **拦截器**：`scopedModelDriven` + `struts1Stack` 桥接 ActionForm 的 setProperty
- **2.5 移除原因**：Struts 1 早已 EOL（2013）；混合版本 jar 在 classpath 冲突排查成本极高
- **迁移方案**：直接用 `RequestAware` / `SessionAware` 接口替代 ActionForm 的 `request/session` 隐式对象

## 对应文档

参见 `struts2-learn/远古-Struts2.3历史插件.md`。
