# Demo 30: SiteMesh 插件（Filter 装饰器）

演示 Struts 7.3.0 `struts2-sitemesh-plugin` + SiteMesh 3.2.1：用 `sitemesh3.xml` + 装饰器 JSP（decorator）自动包装业务 JSP，统一 header/sidebar/footer。

> **注意**：SiteMesh 插件自 Struts 7.x 起被官方标记为 deprecated（按 Spec §11.1）。**新项目推荐迁到 FreeMarker 装饰器或前端 SPA**；本 demo 仅用于学习与维护遗留项目。

## 启动

```bash
mvn -pl demo-30-plugin-sitemesh jetty:run
```

访问：
- 首页：http://localhost:8080/demo-30-plugin-sitemesh/home.action
- 用户列表：http://localhost:8080/demo-30-plugin-sitemesh/user-list.action
- 裸 API（不装饰）：http://localhost:8080/demo-30-plugin-sitemesh/api/raw.action

## 文件清单

- `HomeAction.java` / `UserListAction.java` / `RawApiAction.java`：3 个 Action
- `struts.xml`：声明 3 个 action（jsp 视图）
- `web.xml`：注册 SiteMesh Filter（在 Struts filter 之后）+ StrutsPrepareAndExecuteFilter
- `WEB-INF/sitemesh3.xml`：装饰 mapping（`/*` → `main.jsp`，`/api/*` 排除）
- `WEB-INF/decorators/main.jsp`：装饰器（统一 header / nav / footer）
- `home-page.jsp` / `user-list.jsp` / `raw.jsp`：被装饰的业务页面（只关心 body）

## 对应文档

参见 `struts2-learn/32-插件-SiteMesh.md`