# legacy-demo-06-portlet-tiles

**主题**：Struts 2.5.30 Portlet + Tiles 集成（struts2-portlet-tiles-plugin）

**状态**：**仅配置示例**（不要求 Web 容器跑通，Portlet 需要 Liferay / Pluto / WebSphere Portal）

在 Portlet 容器里，Action 的 render / action / resource 三阶段返回的 JSP
由 Tiles 拼装成完整门户页（header + body + footer）。这是 JSR-168 / JSR-286
时代的经典门户集成方案。

## 启动

```bash
mvn -pl legacy-demo-06-portlet-tiles -am install -DskipTests

# 仅编译验证；Portlet 运行时需 Liferay / Pluto / WebSphere Portal
# http://<portal-host>/legacy-demo-06-portlet-tiles/page.action
```

## 文件清单

- `Page.java`：门户页片段模型（title + body）
- `PageAction.java`：提供 `view()`（render 阶段）和 `submit()`（action 阶段）两个方法
- `struts.xml`：包继承 `struts-default,tiles-default`；`<result type="tiles">portal.page</result>`
- `tiles.xml`：Tiles 3 定义 `portal.page`（header + body + footer 三段拼接）
- `web.xml`：Struts 2 过滤器
- `WEB-INF/tiles/{header,body,footer,layout}.jsp`：Tiles 模板片段

## 关键点

- **依赖**：`struts2-portlet-tiles-plugin-2.5.30.jar` + Tiles 3
- **包继承**：`extends="struts-default,tiles-default"`，Portlet 模式下容器会再追加 `portlet-default`
- **Action 三阶段**：render（首次加载）/ action（表单提交）/ resource（Ajax / 资源）
- **Tiles 模板拼接**：Tiles 通过 `portal.page` definition 把 3 个 JSP 拼成一个门户页
- **6.0 弃用原因**：Portal 时代已过；SPA + REST 成为现代化门户实现
- **完整跑通所需**：Liferay 7.x / Apache Pluto 3.x / IBM WebSphere Portal

## 对应文档

参见 `struts2-learn/历史插件-PortletTiles.md`（如未实现）。
