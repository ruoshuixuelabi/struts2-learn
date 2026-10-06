# ancient-demo-03-sitegraph

**主题**：Struts 2.3.37 Sitegraph 插件（站点依赖图）

Sitegraph 插件分析 Struts 2 应用中 Action 之间的跳转关系，
生成可视化的站点依赖图（PNG/SVG），帮助梳理大型 Struts 项目的导航结构。

## 启动

```bash
mvn -pl ancient-demo-03-sitegraph -am install -DskipTests
```

插件在运行时通过后台线程扫描 `struts.xml`，**不依赖 Web 容器启动**；
访问 `/sitegraph/view.action` 即可看到生成的依赖图。

## 文件清单

- `ActionA.java` / `ActionB.java` / `ActionC.java`：三个 Action 形成链 `C → B → A`
- `ActionCTest.java`：单元测试，验证 chain result 链式跳转正确
- `struts.xml`：定义 actionA / actionB / actionC；B 和 C 通过 `<result type="chain">` 串联
- `web.xml`：注册 Struts 2 过滤器
- `a.jsp`：ActionA 的视图（链终点）

## 关键点

- **依赖**：`struts2-sitegraph-plugin-2.3.x.jar`
- **生成图类型**：DAG（Directed Acyclic Graph），可识别 chain / redirect / dispatcher 三类 result
- **输出格式**：`graphviz dot` → PNG/SVG
- **典型用法**：重构老 Struts 项目时，先跑 sitegraph 看 Action 间的耦合度
- **2.5 移除原因**：插件依赖 graphviz 二进制，与 Struts 核心解耦不够；现代 IDE/工具已能生成等价图

## 对应文档

参见 `struts2-learn/远古-Struts2.3历史插件.md`。
