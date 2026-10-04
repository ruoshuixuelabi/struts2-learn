# Demo 33: JasperReports Plugin（PDF / Excel / HTML 报表）

> 对应章节：[`35-插件-JasperReports.md`](../../../struts2-learn/35-插件-JasperReports.md)

## 主题

`struts2-jasperreports-plugin` 把 .jrxml 编译为 JasperPrint，再用 result type="jasper" 导出 PDF / Excel / HTML。
本 demo 演示两种用法：

1. **静态模板**：`location` 指向 `.jrxml`，`dataSource` 指向 Action 上的 getter（OGNL 注入）
2. **运行时编译**：Action 直接 `JasperCompileManager.compileReport(...)`，暴露 `getJasperPrint()`

## 关键点

- 继承 `jasperreports-default` 包才能用 jasper result
- `format` 参数：`PDF / XLS / XLSX / HTML / CSV / RTF / DOCX`
- `dataSource` 既可以传 OGNL 表达式名（会自动调用 getter），也可以传 Action 已暴露的 `JRDataSource` / `JasperPrint`
- 浏览器访问会触发文件下载（Content-Disposition: attachment）

## 运行

```bash
mvn jetty:run
# 访问：
#   http://localhost:8080/demo-33-plugin-jasperreports/report/user-list.action        -> PDF
#   http://localhost:8080/demo-33-plugin-jasperreports/report/user-list.action?resultName=xls   -> XLS
#   http://localhost:8080/demo-33-plugin-jasperreports/report/user-list.action?resultName=html  -> HTML
#   http://localhost:8080/demo-33-plugin-jasperreports/report/dynamic.action        -> PDF（运行时编译）
```

## 文件结构

```
demo-33-plugin-jasperreports/
├── pom.xml
├── README.md
├── src/main/java/com/example/learn/struts2/demo33/
│   ├── UserReportAction.java
│   └── DynamicReportAction.java
├── src/main/resources/
│   ├── struts.xml
│   └── jasper/
│       ├── user-list.jrxml
│       └── dynamic.jrxml
├── src/main/webapp/WEB-INF/web.xml
├── src/main/webapp/index.html
└── src/test/java/com/example/learn/struts2/demo33/UserReportActionTest.java
```