# Demo 32: Javatemplates Plugin（纯 Java 模板 / 纯字符串模板）

> 对应章节：[`34-插件-Javatemplates.md`](../../../struts2-learn/34-插件-Javatemplates.md)

## 主题

`struts2-javatemplates-plugin` 用 `.java.html` 文件写模板；本 demo 进一步演示**零 JSP** 的写法：
在 struts.xml 里用 `result type="javanano"` 或 `result type="plainText"`，直接把 Action 返回的字符串当作响应体输出。

## 关键点

- **不写 JSP / 模板文件**——`Action.getTemplate()` 直接返回完整 HTML 字符串
- 两种 result type：
  - `javanano`：OGNL 表达式把模板字符串写到响应（如 `${template}`）
  - `plainText`：`location` 指向 Action 的属性名（如 `template`）
- Action 暴露 getter 即可（`getTemplate()`、`getMessage()`、`getLines()`）

## 运行

```bash
mvn jetty:run
# 浏览：
#   http://localhost:8080/demo-32-plugin-javatemplates/
#   http://localhost:8080/demo-32-plugin-javatemplates/javatemplates/nano.action
#   http://localhost:8080/demo-32-plugin-javatemplates/javatemplates/plain.action
```

## 文件结构

```
demo-32-plugin-javatemplates/
├── pom.xml
├── README.md
├── src/main/java/com/example/learn/struts2/demo32/JavatemplatesAction.java
├── src/main/resources/struts.xml
├── src/main/webapp/WEB-INF/web.xml
├── src/main/webapp/index.html
└── src/test/java/com/example/learn/struts2/demo32/JavatemplatesActionTest.java
```