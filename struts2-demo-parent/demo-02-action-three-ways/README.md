# Demo 02: Action 三种实现方式

对比演示 Struts 2 Action 的三种实现方式：
1. **POJO**：普通 Java 类，无需继承任何接口
2. **Action 接口**：实现 `org.apache.struts2.action.Action`
3. **注解**：用 `@Action` + `@Result`（来自 `org.apache.struts2.convention.annotation.*`，需 Convention 插件）

## 启动

```bash
mvn -pl demo-02-action-three-ways jetty:run
```

访问：http://localhost:8080/demo-02-action-three-ways/index.jsp

## 对应文档

参见 `struts2-learn/04-Action详解.md`