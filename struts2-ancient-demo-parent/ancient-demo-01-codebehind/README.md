# ancient-demo-01-codebehind

**主题**：Struts 2.3.37 Codebehind 插件（零配置）

Codebehind 插件是 2.5 之前移除的"约定优于配置"插件：
只要 Action 类名遵循 `XxxAction`，并且 `Xxx.jsp` 与 Action 在同一目录下，
插件会自动生成 URL 映射（无需写 `struts.xml` 的 `<action>` 节点）。

## 启动

```bash
# 先 install 父项目
mvn -pl ancient-demo-01-codebehind -am install -DskipTests

# 部署到 Tomcat 8.5+，访问：
# http://localhost:8080/ancient-demo-01-codebehind/hello.action
```

## 文件清单

- `HelloAction.java`：普通 Action，返回 `SUCCESS`，渲染 `/hello.jsp`
- `IndexAction.java`：演示 Codebehind 自动映射的 Action（无需 `struts.xml` 配置）
- `struts.xml`：仅声明 `<constant name="struts.codebehind" value="true"/>` 即可启用
- `web.xml`：注册 Struts 2 过滤器
- `hello.jsp` / `index.jsp`：Action 对应的 JSP 视图

## 关键点

- **插件靠 classpath 激活**：`struts2-codebehind-plugin-2.3.x.jar` 存在即生效
- **常量声明**：`struts.codebehind = true` 是惯例写法（非必需，但 IDE 提示更友好）
- **Action 名 → URL 约定**：`IndexAction` 自动暴露 `index.action`（首字母小写 + 去掉 `Action` 后缀）
- **JSP 约定**：`/IndexAction.java` 同目录的 `/index.jsp` 自动作为 success result
- **2.5 移除原因**：约定魔法太多、调试困难、被 Convention Plugin 取代

## 对应文档

参见 `struts2-learn/远古-Struts2.3历史插件.md`（如未实现，可参考 struts2 官方 wiki 的 Codebehind Plugin 章节）。
