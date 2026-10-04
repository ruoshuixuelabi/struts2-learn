# Demo 34: JFreeChart Plugin（服务端 PNG 图表）

> 对应章节：[`36-插件-JFreeChart.md`](../../../struts2-learn/36-插件-JFreeChart.md)

## 主题

`struts2-jfreechart-plugin` 用 `chart` ResultType 把 `JFreeChart` 对象直接渲染成 PNG 流。
本 demo 演示三种典型图表：饼图、柱状图、折线图，全部用 `<img src="...action">` 引用。

## 关键点

- 继承 `jfreechart-default` 包才能用 chart result
- Action 必须暴露 `getChart()` getter（OGNL 自动注入）
- `<param name="width">` / `<param name="height">` 调整像素大小（默认 300x200，偏小）
- 适用场景：PDF 报表嵌入图表、邮件图片附件、服务端离线图表
- 现代 Web 图表场景更推荐 ECharts / Chart.js（前端方案）

## 运行

```bash
mvn jetty:run
# 浏览：
#   http://localhost:8080/demo-34-plugin-jfreechart/
```

## 文件结构

```
demo-34-plugin-jfreechart/
├── pom.xml
├── README.md
├── src/main/java/com/example/learn/struts2/demo34/
│   ├── SalesPieChartAction.java
│   ├── SalesBarChartAction.java
│   └── LineChartAction.java
├── src/main/resources/struts.xml
├── src/main/webapp/WEB-INF/web.xml
├── src/main/webapp/index.html
└── src/test/java/com/example/learn/struts2/demo34/SalesPieChartActionTest.java
```