# ancient-demo-05-tiles3

**主题**：Struts 2.3.37 Tiles 3 集成（struts2-tiles3-plugin）

Tiles 是经典的 JSP 布局框架：把 header / body / footer 抽成可复用片段，
按"插槽（attribute）"拼装成完整页面。Struts 2 通过 `struts2-tiles3-plugin`
提供 `<result type="tiles">` 让 Action 返回 Tiles 定义名。

## 启动

```bash
mvn -pl ancient-demo-05-tiles3 -am install -DskipTests

# 部署到 Tomcat 8.5+，访问：
# http://localhost:8080/ancient-demo-05-tiles3/home.action
```

## 文件清单

- `LayoutAction.java`：返回 SUCCESS，由 tiles 渲染 `home` definition
- `struts.xml`：包继承 `tiles-default`；`<result type="tiles">home</result>`
- `tiles.xml`：Tiles 定义（baseLayout 模板 + home 派生）
- `web.xml`：Struts 2 过滤器 + Tiles 监听器
- `layout.jsp`：Tiles 母版（带 `<tiles:insertAttribute>`）
- `home-body.jsp`：home 页面的 body 插槽内容

## 关键点

- **依赖**：`struts2-tiles3-plugin-2.3.x.jar` + `org.apache.tiles:tiles-jsp` + `tiles-core`
- **包继承**：必须 `extends="tiles-default"`
- **Tiles 3 定义**：使用 `<put-attribute name="...">`；支持 `extends` 派生定义
- **母版与插槽**：`layout.jsp` 用 `<tiles:insertAttribute name="body"/>` 占位
- **2.5 移除原因**：Tiles 3 升级到 4 时引入大量注解配置；Struts 团队选择保留 `tiles-default` 包而抛弃 `tiles3-default`
- **现代替代**：Apache Tiles 4 + FreeMarker / Thymeleaf 模板引擎

## 对应文档

参见 `struts2-learn/远古-Struts2.3历史插件.md`。
