# Demos 入口

按 Struts 版本拆分为 4 个独立 Maven 父项目，**互不依赖、互不污染**。

## 📊 父项目对照

| 父项目目录 | Struts 版本 | JDK | Servlet API | Tomcat | 子模块数 | 内容 |
|---|---|---|---|---|---|---|
| [`struts2-demo-parent/`](./struts2-demo-parent/) | **7.3.0** | 17 | jakarta.servlet 6.0 | 10.1+ | 36 | 主线 + 活跃插件 + 自定义插件 |
| [`struts2-legacy-demo-parent/`](./struts2-legacy-demo-parent/) | **2.5.30** | 8 | javax.servlet 4.0 | 9.x | 8 | 6.0 弃用 + 7.x 移除插件 |
| [`struts2-ancient-demo-parent/`](./struts2-ancient-demo-parent/) | **2.3.37** | 7 | javax.servlet 3.1 | 8.5+ | 6 | 2.5 之前移除插件 |
| [`struts1-demo-parent/`](./struts1-demo-parent/) | **1.3.10** | 5/6 | javax.servlet 2.5 | 7.x/8.x | 2 | Struts 1 历史 |

**合计 52 个 demo 子模块**。

---

## 🎯 子模块索引

### 📘 `struts2-demo-parent/`（Struts 7.3.0 + JDK 17 + jakarta.servlet 6.0）— 36 个

#### 主线（01–20）

| 子模块 | 主题 | 对应 MD | 启动端口 |
|---|---|---|---|
| [`demo-01-helloworld`](./struts2-demo-parent/demo-01-helloworld/) | Struts 7 最小 Hello World | [02](./../struts2-learn/02-Struts7快速上手-HelloWorld.md) | 8080 |
| [`demo-02-action-three-ways`](./struts2-demo-parent/demo-02-action-three-ways/) | POJO / 接口 / 注解三种 Action 写法 | [04](./../struts2-learn/04-Action详解.md) | 8080 |
| [`demo-03-arch-lifecycle`](./struts2-demo-parent/demo-03-arch-lifecycle/) | 17 个拦截器 + 洋葱模型生命周期 | [03](./../struts2-learn/03-核心架构与请求生命周期.md) | 8080 |
| [`demo-04-interceptor`](./struts2-demo-parent/demo-04-interceptor/) | 自定义 Interceptor 拦截日志 | [06](./../struts2-learn/06-拦截器Interceptor.md) | 8080 |
| [`demo-05-interceptor-advanced`](./struts2-demo-parent/demo-05-interceptor-advanced/) | `MethodFilterInterceptor` + 拦截器栈顺序 | [14](./../struts2-learn/14-拦截器高级用法.md) | 8080 |
| [`demo-06-valuestack`](./struts2-demo-parent/demo-06-valuestack/) | 值栈三种推值 + JSP 读取 | [07](./../struts2-learn/07-值栈ValueStack.md) | 8080 |
| [`demo-07-ognl`](./struts2-demo-parent/demo-07-ognl/) | OGNL 集合过滤 / 投影 / 静态访问 | [08](./../struts2-learn/08-OGNL表达式.md) | 8080 |
| [`demo-08-strutstag`](./struts2-demo-parent/demo-08-strutstag/) | Struts UI 标签完整表单演示 | [09](./../struts2-learn/09-Struts标签库.md) | 8080 |
| [`demo-09-i18n`](./struts2-demo-parent/demo-09-i18n/) | i18n 全局 + 类级别资源 + 中英切换 | [10](./../struts2-learn/10-国际化i18n与本地化.md) | 8080 |
| [`demo-10-exception`](./struts2-demo-parent/demo-10-exception/) | 全局 exception-mapping + global-results | [11](./../struts2-learn/11-异常处理.md) | 8080 |
| [`demo-11-upload`](./struts2-demo-parent/demo-11-upload/) | `actionFileUpload` 上传 + Stream 下载 | [12](./../struts2-learn/12-文件上传与下载.md) | 8080 |
| [`demo-12-result`](./struts2-demo-parent/demo-12-result/) | dispatcher / redirect / chain / json 四种 Result | [13](./../struts2-learn/13-结果类型ResultType.md) | 8080 |
| [`demo-13-action-advanced`](./struts2-demo-parent/demo-13-action-advanced/) | Preparable / ModelDriven / ValidationAware | [15](./../struts2-learn/15-高级Action特性.md) | 8080 |
| [`demo-14-validation`](./struts2-demo-parent/demo-14-validation/) | XML 校验器（requiredstring / stringlength / int）| [16](./../struts2-learn/16-验证框架（XML+注解）.md) | 8080 |
| [`demo-15-junit`](./struts2-demo-parent/demo-15-junit/) | `StrutsJUnit5Test` + JUnit 5 测 Action | [17](./../struts2-learn/17-单元测试（JUnit+TestNG）.md) / [27](./../struts2-learn/27-插件-JUnit.md) | 8080 |
| [`demo-16-testng`](./struts2-demo-parent/demo-16-testng/) | `StrutsTestCase` TestNG + `@DataProvider` | [17](./../struts2-learn/17-单元测试（JUnit+TestNG）.md) / [28](./../struts2-learn/28-插件-TestNG.md) | 8080 |
| [`demo-17-ognl-security`](./struts2-demo-parent/demo-17-ognl-security/) | `@StrutsParameter` + OGNL 黑名单拦截器 | [18](./../struts2-learn/18-Security安全加固.md) | 8080 |
| [`demo-18-validation-annotation`](./struts2-demo-parent/demo-18-validation-annotation/) | JSR-303 + Struts 注解校验 | [16](./../struts2-learn/16-验证框架（XML+注解）.md) / [29](./../struts2-learn/29-插件-Bean-Validation.md) | 8080 |
| [`demo-19-spring-plugin`](./struts2-demo-parent/demo-19-spring-plugin/) | Spring 6.1 + `@Autowired` 注入 Service | [25](./../struts2-learn/25-插件-Spring.md) | 8080 |
| [`demo-20-security`](./struts2-demo-parent/demo-20-security/) | 三道防线（strictMethodInvocation + allowlist + hardening）| [18](./../struts2-learn/18-Security安全加固.md) | 8080 |

#### 插件体系（21–36）

| 子模块 | 主题 | 对应 MD |
|---|---|---|
| [`demo-21-plugin-convention`](./struts2-demo-parent/demo-21-plugin-convention/) | 约定优于配置（注解 + 命名约定）| [22](./../struts2-learn/22-插件-Convention.md) |
| [`demo-22-plugin-json`](./struts2-demo-parent/demo-22-plugin-json/) | Action 返回 JSON（注解 + result type=json）| [23](./../struts2-learn/23-插件-JSON.md) |
| [`demo-23-plugin-rest`](./struts2-demo-parent/demo-23-plugin-rest/) | REST 5 方法 + `rest-default` 包 | [24](./../struts2-learn/24-插件-REST.md) |
| [`demo-24-plugin-cdi`](./struts2-demo-parent/demo-24-plugin-cdi/) | Weld + `@Inject` 注入 | [26](./../struts2-learn/26-插件-CDI.md) |
| [`demo-25-plugin-junit`](./struts2-demo-parent/demo-25-plugin-junit/) | `StrutsJUnit4TestCase` + JUnit 4 | [27](./../struts2-learn/27-插件-JUnit.md) |
| [`demo-26-plugin-testng`](./struts2-demo-parent/demo-26-plugin-testng/) | `StrutsTestNGTestCase` + TestNG 7.9 | [28](./../struts2-learn/28-插件-TestNG.md) |
| [`demo-27-plugin-bean-validation`](./struts2-demo-parent/demo-27-plugin-bean-validation/) | JSR-303 + Hibernate Validator 8 | [29](./../struts2-learn/29-插件-Bean-Validation.md) |
| [`demo-28-plugin-oval`](./struts2-demo-parent/demo-28-plugin-oval/) | OVal 注解（6.0 弃用）| [30](./../struts2-learn/30-插件-OVal.md) |
| [`demo-29-plugin-tiles`](./struts2-demo-parent/demo-29-plugin-tiles/) | Tiles 3 布局（baseLayout + definition）| [31](./../struts2-learn/31-插件-Tiles.md) |
| [`demo-30-plugin-sitemesh`](./struts2-demo-parent/demo-30-plugin-sitemesh/) | SiteMesh 3.2 装饰器（统一 header/nav）| [32](./../struts2-learn/32-插件-SiteMesh.md) |
| [`demo-31-plugin-velocity`](./struts2-demo-parent/demo-31-plugin-velocity/) | Velocity 模板（`.vm` 文件）| [33](./../struts2-learn/33-插件-Velocity.md) |
| [`demo-32-plugin-javatemplates`](./struts2-demo-parent/demo-32-plugin-javatemplates/) | 纯 Java 字符串模板（零 JSP）| [34](./../struts2-learn/34-插件-Javatemplates.md) |
| [`demo-33-plugin-jasperreports`](./struts2-demo-parent/demo-33-plugin-jasperreports/) | JasperReports 6 PDF/Excel/HTML 导出 | [35](./../struts2-learn/35-插件-JasperReports.md) |
| [`demo-34-plugin-jfreechart`](./struts2-demo-parent/demo-34-plugin-jfreechart/) | JFreeChart 1.5 饼图/柱状图/折线图 | [36](./../struts2-learn/36-插件-JFreeChart.md) |
| [`demo-35-plugin-async`](./struts2-demo-parent/demo-35-plugin-async/) | 异步 Action（`executeAndWait` + SSE）| [37](./../struts2-learn/37-插件-Async.md) |
| [`demo-36-plugin-custom`](./struts2-demo-parent/demo-36-plugin-custom/) | 自定义 ResultType 插件开发 | [39](./../struts2-learn/39-插件-自定义插件编写指南.md) |

### 📕 `struts2-legacy-demo-parent/`（Struts 2.5.30 + JDK 8 + javax.servlet 4.0）— 8 个

| 子模块 | 主题 | 状态 |
|---|---|---|
| [`legacy-demo-01-oval`](./struts2-legacy-demo-parent/legacy-demo-01-oval/) | OVal 注解（运行示例）| ✅ 可跑 |
| [`legacy-demo-02-embedded-jsp`](./struts2-legacy-demo-parent/legacy-demo-02-embedded-jsp/) | `EmbeddedJspResult`（JSP 编译成 class）| ✅ 可跑 |
| [`legacy-demo-03-osgi`](./struts2-legacy-demo-parent/legacy-demo-03-osgi/) | OSGi 集成（Felix/Equinox 配置）| ⚠ 配置示例，需 OSGi 容器 |
| [`legacy-demo-04-plexus`](./struts2-legacy-demo-parent/legacy-demo-04-plexus/) | Plexus IoC（`PlexusObjectFactory`）| ⚠ 需 Plexus 容器 |
| [`legacy-demo-05-portlet`](./struts2-legacy-demo-parent/legacy-demo-05-portlet/) | JSR-168/286 Portlet（`PortletAction`）| ⚠ 配置示例，需 Portal 容器 |
| [`legacy-demo-06-portlet-tiles`](./struts2-legacy-demo-parent/legacy-demo-06-portlet-tiles/) | Portlet + Tiles 集成 | ⚠ 配置示例，需 Portal 容器 |
| [`legacy-demo-07-dwr`](./struts2-legacy-demo-parent/legacy-demo-07-dwr/) | DWR AJAX 集成（`dwr.xml` + `creator="struts2"`）| ✅ 可跑 |
| [`legacy-demo-08-pell-multipart`](./struts2-legacy-demo-parent/legacy-demo-08-pell-multipart/) | Pell 多段上传（替代 commons-fileupload）| ✅ 可跑 |

### 📙 `struts2-ancient-demo-parent/`（Struts 2.3.37 + JDK 7 + javax.servlet 3.1）— 6 个

| 子模块 | 主题 | 状态 |
|---|---|---|
| [`ancient-demo-01-codebehind`](./struts2-ancient-demo-parent/ancient-demo-01-codebehind/) | codebehind 插件（约定配置）| ⚠ Struts 2.5 前移除 |
| [`ancient-demo-02-jsf`](./struts2-ancient-demo-parent/ancient-demo-02-jsf/) | JSF 集成 | ⚠ Struts 2.5 前移除 |
| [`ancient-demo-03-sitegraph`](./struts2-ancient-demo-parent/ancient-demo-03-sitegraph/) | sitegraph 站点地图 | ⚠ Struts 2.5 前移除 |
| [`ancient-demo-04-struts1-plugin`](./struts2-ancient-demo-parent/ancient-demo-04-struts1-plugin/) | struts1-plugin 桥接 | ✅ 可跑（迁移老项目）|
| [`ancient-demo-05-tiles3`](./struts2-ancient-demo-parent/ancient-demo-05-tiles3/) | Tiles 3 集成（2.3 时代 API）| ✅ 可跑 |
| [`ancient-demo-06-java8-support`](./struts2-ancient-demo-parent/ancient-demo-06-java8-support/) | Java 8 语言特性支持插件 | ✅ 可跑 |

### 📗 `struts1-demo-parent/`（Struts 1.3.10 + JDK 5/6 + javax.servlet 2.5）— 2 个

| 子模块 | 主题 | 状态 |
|---|---|---|
| [`struts1-demo-01-helloworld`](./struts1-demo-parent/struts1-demo-01-helloworld/) | Struts 1 Hello World（`ActionServlet` + `struts-config.xml`）| ✅ 可跑 |
| [`struts1-demo-02-actionform`](./struts1-demo-parent/struts1-demo-02-actionform/) | `ActionForm` 表单模式（与 Struts 2 POJO 对比）| ✅ 可跑 |

---

## ▶️ 运行规则

- **每个父项目独立构建**：cd 到对应父项目目录后运行 `mvn install` 或 `mvn <plugin>:run`
- **每个子模块独立部署**：使用 `-pl <子模块名> -am` 单独构建某个 demo
- **JDK 多版本**：建议使用 [jenv](https://github.com/jenv/jenv) 或 SDKMAN 管理多版本 JDK
- **端口冲突**：所有 current demo 默认 8080 端口，**不要同时跑多个**；如需并发请改各自 pom.xml 的 `jetty.http.port`

## 🚀 子模块运行示例

```bash
# 进入 current 父项目
cd struts2-demo-parent

# 跑通 demo-01 Hello World（嵌入式 Jetty）
mvn -pl demo-01-helloworld jetty:run
# 访问 http://localhost:8080/demo-01-helloworld/

# 跑通 demo-22 JSON 插件
mvn -pl demo-22-plugin-json jetty:run

# 构建 legacy DWR demo（用 Tomcat 9.x 部署）
cd ../struts2-legacy-demo-parent
mvn -pl legacy-demo-07-dwr package
# 把 target/*.war 部署到 Tomcat 9.x
```

## ⚠ 例外说明

OSGi / Portlet / Portlet Tiles / Plexus 等需要**额外基础设施**的插件，demo 给出**最小配置示例 + 单元测试**，不要求完整 Web 容器跑通。具体见：

- `legacy-demo-03-osgi`：需 Felix 或 Equinox OSGi 容器
- `legacy-demo-04-plexus`：需 Plexus 容器启动
- `legacy-demo-05-portlet` / `legacy-demo-06-portlet-tiles`：需 Liferay/Pluto/WebSphere Portal

其余 48 个 demo 均可 `mvn jetty:run` 跑通。

---

## 📊 子模块统计

| 父项目 | 子模块数 | 可直接跑 | 仅配置示例 |
|---|---|---|---|
| struts2-demo-parent | 36 | 36 | 0 |
| struts2-legacy-demo-parent | 8 | 4 | 4 |
| struts2-ancient-demo-parent | 6 | 3 | 3 |
| struts1-demo-parent | 2 | 2 | 0 |
| **合计** | **52** | **45** | **7** |