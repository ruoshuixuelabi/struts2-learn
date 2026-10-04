# Demo 29: Tiles 插件（页面模板组合）

演示 Struts 7.3.0 `struts2-tiles-plugin` + Apache Tiles 3.0.8：用 `tiles.xml` 定义 layout 模板 + 子页面继承 baseLayout 的方式组合页面（header / menu / body / footer）。

## 启动

```bash
mvn -pl demo-29-plugin-tiles jetty:run
```

访问：
- 首页（home definition）：http://localhost:8080/demo-29-plugin-tiles/home.action
- 用户列表（user.list definition）：http://localhost:8080/demo-29-plugin-tiles/user-list.action

## 文件清单

- `HomeAction.java` / `UserListAction.java`：Action 返回 SUCCESS
- `struts.xml`：声明 `tiles` result type + action 映射
- `web.xml`：注册 Tiles 监听器 + StrutsPrepareAndExecuteFilter
- `WEB-INF/tiles.xml`：tiles definition 定义（baseLayout + home.tiles + user.list.tiles）
- `WEB-INF/layouts/layout.jsp`：基础模板（占位 header / menu / body / footer）
- `WEB-INF/layouts/header.jsp` / `menu.jsp` / `user-menu.jsp` / `footer.jsp`：可复用片段
- `WEB-INF/home/home.jsp` / `WEB-INF/user/list.jsp`：业务正文

## 对应文档

参见 `struts2-learn/31-插件-Tiles.md`