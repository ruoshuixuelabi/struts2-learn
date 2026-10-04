# Struts 2 Ancient Demo Parent（Struts 2.3.37）

**远古版本**（2018 年 EOL），覆盖 2.5 之前移除的插件。

- Struts 版本：2.3.37（末班车）
- JDK：7
- Servlet API：javax.servlet 3.1
- Tomcat：8.5+

## 包含 6 个子模块

- `ancient-demo-01-codebehind`（Codebehind 零配置，2.5 前移除）
- `ancient-demo-02-jsf`（JSF 桥接，2.5 前移除）
- `ancient-demo-03-sitegraph`（站点依赖图，2.5 前移除）
- `ancient-demo-04-struts1-plugin`（Struts 1→2 桥接，2.5 前移除）
- `ancient-demo-05-tiles3`（Tiles 3 集成，2.5 前移除）
- `ancient-demo-06-java8-support`（Java 8 lambda 支持，2.5 前移除）

## 注意事项

- 2.3.x 在 Maven Central 已 EOL，部分依赖可能需要手动指定镜像
- 此父项目独立构建，不要与 current/legacy 混合
- 部署用 Tomcat 8.5+