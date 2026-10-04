# Struts 2 Legacy Demo Parent（Struts 2.5.30）

**遗留版本**，覆盖 6.0 弃用 + 7.x 移除的插件。

- Struts 版本：2.5.30
- JDK：8
- Servlet API：javax.servlet 4.0
- Tomcat：9.x

## 包含 8 个子模块

- `legacy-demo-01-oval`（OVal 校验，6.0 弃用）
- `legacy-demo-02-embedded-jsp`（嵌入式 JSP，6.0 弃用）
- `legacy-demo-03-osgi`（OSGi 集成，6.0 弃用，**仅配置示例**）
- `legacy-demo-04-plexus`（Plexus IoC，6.0 弃用）
- `legacy-demo-05-portlet`（Portlet 容器，6.0 弃用，**仅配置示例**）
- `legacy-demo-06-portlet-tiles`（Portlet + Tiles，6.0 弃用，**仅配置示例**）
- `legacy-demo-07-dwr`（DWR 远程调用，7.x 移除）
- `legacy-demo-08-pell-multipart`（Pell Multipart，7.x 移除，**仅配置示例**）

## 注意事项

- 此父项目与 `struts2-demo-parent/` **不可混合构建**：不同 Struts 主版本会冲突
- 标"仅配置示例"的 demo 不要求 Web 容器跑通（OSGi/Portlet/Pell 需要额外基础设施）
- 由于 2.5.30 的 Servlet API 是 javax.servlet，部署到 Tomcat 9.x（不要用 Tomcat 10+）

## 编译

```bash
mvn install -DskipTests
```

部署：把 `legacy-demo-XX/target/*.war` 拷到 Tomcat 9.x 的 `webapps/`。