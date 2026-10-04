# Struts 6 → 7 迁移指南（针对本仓库 demo）

> 本文档对照官方 [Struts 6.x.x to 7.x.x migration guide](https://cwiki.apache.org/confluence/x/tAljFQ)
> 梳理**影响本仓库 tutorial demo 的全部变更**，以及在本仓库里**已应用的对策**。
>
> 适用版本：Struts 7.3.0 GA（[2026-08-01 发布](https://struts.apache.org/announce.html)）。
> 当前仓库 demo 全部基于此版本测试通过（`mvn test` 全 35 模块 BUILD SUCCESS）。

---

## 1. 运行环境

| 项 | 旧（Struts 6.x） | 新（Struts 7.x） | 本仓库 |
|---|---|---|---|
| JDK 最低 | 11 | **17** | JDK 17 / 21 均可（CI 用 21）|
| Servlet API | javax.servlet 5 | **jakarta.servlet 6** | 已全面切换（`jakarta.servlet:jakarta.servlet-api`）|
| 应用容器 | Tomcat 10 / Jetty 11 | Tomcat 11 / Jetty 12 | 嵌入式 Jetty 12（Maven plugin）|

---

## 2. 包名迁移（com.opensymphony.xwork2 → org.apache.struts2）

XWork 2 已合并到 Struts 2。所有 `com.opensymphony.xwork2.*` 与 `org.opensymphony.xwork2.*`
包下的类迁移到 `org.apache.struts2.*`，包括：

| 旧类 | 新类 |
|---|---|
| `com.opensymphony.xwork2.Action` | `org.apache.struts2.action.Action` |
| `com.opensymphony.xwork2.ActionChainResult` | `org.apache.struts2.result.ActionChainResult` |
| `com.opensymphony.xwork2.TextProvider` 等 | `org.apache.struts2.text.*` |
| `com.opensymphony.xwork2.LocaleProvider` 等 | `org.apache.struts2.locale.*` |
| `com.opensymphony.xwork2.interceptor.MethodFilterInterceptor` | `org.apache.struts2.interceptor.MethodFilterInterceptor` |

**本仓库对策**：

- 全部 demo 已切换为 `org.apache.struts2.*`
- **示例**：`demo-05 AuthInterceptor extends org.apache.struts2.interceptor.MethodFilterInterceptor`
- `Action.SUCCESS` / `Action.INPUT` / `Action.ERROR` 等常量从
  `org.apache.struts2.action.Action` 取（不是 `com.opensymphony.xwork2.Action`）

---

## 3. Servlet API：javax → jakarta

```xml
<!-- 旧 -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
</dependency>

<!-- 新（本仓库当前） -->
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
    <scope>provided</scope>
</dependency>
```

JSP 标签 import：

```jsp
<%-- 旧 --%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%-- 新 --%>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
```

`AsyncContext` / `AsyncListener` / `AsyncEvent` 全部在 `jakarta.servlet.*`。

---

## 4. 安全默认值收紧（OGNL / 参数注入）

Struts 7.x 默认更严格。以下常量**全部默认 true**，必须显式关闭才能使用 demo 里的宽松行为：

| 常量 | 新默认 | 含义 |
|---|---|---|
| `struts.mapper.alwaysSelectFullNamespace` | `true` | 强制 namespace 精确匹配 |
| `struts.actionConfig.fallbackToEmptyNamespace` | `false` | 不允许 fallback 到空 namespace |
| `struts.ognl.allowStaticFieldAccess` | `false` | OGNL 不能访问静态字段 |
| `struts.ognl.disallowCustomOgnlMap` | `true` | 仅 HashMap/TreeMap/LinkedHashMap 可实例化 |
| `struts.ognl.expressionMaxLength` | `150` | OGNL 表达式最长 150 字符（旧 200）|
| `struts.parameters.requireAnnotations` | `true` | 参数注入必须加 `@StrutsParameter` |
| `struts.disallowProxyObjectAccess` | `true` | 不允许访问 Spring/Hibernate 代理 |
| `struts.disallowDefaultPackageAccess` | `true` | 不允许访问默认包 |
| `struts.allowlist.enable` | `true` | 启用 allowlist（替代 exclusion list）|

### 4.1 本仓库对策：测试模式放宽

为了保持 demo 教学性（不强制每个 setter 加 `@StrutsParameter`），在测试基类
`struts2-demo-test-support/.../StrutsJUnit5Test.java` 关闭：

```java
this.dispatcherInitParams.putIfAbsent("struts.parameters.requireAnnotations", "false");
```

子模块 `struts.xml` 里若显式设置同名常量会覆盖此处。

### 4.2 `@StrutsParameter` 注解（生产推荐）

```java
import org.apache.struts2.convention.annotation.StrutsParameter;

public class UserAction extends ActionSupport {
    private String username;

    @StrutsParameter                    // 显式标记可注入参数
    public void setUsername(String u) { this.username = u; }

    public String getUsername() { return username; }
}
```

本仓库 demo 暂未引入（教学示例用宽松模式），生产项目必须开启。

---

## 5. 移除 / 改名 的拦截器

### 5.1 `fileUpload` → `actionFileUpload`

```xml
<!-- 旧（Struts 6.x） -->
<interceptor-ref name="fileUpload">
    <param name="maximumSize">2097152</param>
    <param name="allowedTypes">image/png,image/jpeg</param>
</interceptor-ref>

<!-- 新（Struts 7.x） -->
<interceptor-ref name="actionFileUpload">
    <param name="maximumSize">2097152</param>
    <param name="allowedTypes">image/png,image/jpeg</param>
</interceptor-ref>
```

类名同步：`org.apache.struts2.interceptor.FileUploadInterceptor` →
`org.apache.struts2.interceptor.ActionFileUploadInterceptor`。

**本仓库对策**：
- `demo-11 upload` 的 `struts.xml` 已使用 `actionFileUpload`
- `struts-default` 包里的 `defaultStack` 自身也已切到 `actionFileUpload`

### 5.2 `executeAndWait` → `execAndWait`

Struts 7.x 把拦截器注册名改为 camelCase，**旧名已移除**：

```xml
<!-- 旧 -->
<interceptor-ref name="executeAndWait">
    <param name="delay">100ms</param>
</interceptor-ref>

<!-- 新（本仓库 demo-35 当前） -->
<interceptor-ref name="execAndWait">
    <param name="delay">100ms</param>
    <param name="delaySleepInterval">50</param>
</interceptor-ref>
```

**本仓库对策**：
- `demo-35 plugin-async` 的 `struts.xml` 已用 `execAndWait`
- README、Java 类注释也同步更新为 `execAndWait`

### 5.3 `ExecuteAndWaitHandler` 接口废弃

老版本要求 action 实现 `ExecuteAndWaitHandler` 接口提供 `getResult()` / `isDone()`。
Struts 2.5+ 拦截器已**不再调用**这两个方法。Struts 7.x 接口本身仍存在（向后兼容），
但**新代码不要实现**。

**本仓库对策**：`LongTaskAction` / `TaskCreateAction` 都不再实现该接口。

---

## 6. 移除的插件

| 插件 | 状态 | 替代方案 |
|---|---|---|
| Portlet 插件 | 移除 | 不在 Struts 7.x 内核，迁移到 Portlet 容器原生方案 |
| Pell Multipart 插件 | 移除 | 改用 `actionFileUpload` 拦截器 + Jakarta Servlet 6 file upload |
| DWR Plugin | 移除 | 改用原生 DWR 集成 |
| **Sitemesh 插件** | **移除** | 改用 Sitemesh 3 直接集成（不通过 Struts plugin）|

**本仓库对策**：
- `demo-30 plugin-sitemesh`：仍保留但作为"sitemesh 3 与 Struts 7.x 的共存演示"。
  测试不依赖 plugin xml，只验证装饰器是否能拦截响应。

---

## 7. FreeMarker 模板变量重命名

```ftl
<#-- 旧 -->
${parameters.userName}

<#-- 新 -->
${attributes.userName}
```

组件 tag 内：

```ftl
<@s.textfield name="userName" value="%{attributes.userName}" />
```

**本仓库对策**：本仓库暂无 FreeMarker demo（仅有 Velocity demo-31、Java Templates demo-32），
不受影响。

---

## 8. 8 编译 / 测试工具链调整

| 旧 | 新 | 备注 |
|---|---|---|
| Struts 6.x | **Struts 7.3.0** | 父 pom `struts2.version` |
| JUnit 4 / TestNG 7 | **JUnit 5 + TestNG 7** | Struts 7.x 自带 `junit-plugin` 只提供 `StrutsJUnit4TestCase`（JUnit 4 风格），需自行写 `@BeforeEach` 包装 |
| Surefire 2.x | **Surefire 3.2.5** | TestNG provider artifactId 是 `surefire-testng`（不是 `-provider` 后缀）|
| DTD 资源 | 仍是 `struts-6.0.dtd` | `struts-7.0.dtd` / `struts-7.3.0.dtd` 当前 **404**。Struts 7.x 配置 schema 与 6.x 兼容，可继续用 `struts-6.0.dtd` |

### 8.1 本仓库 Surefire + TestNG 集成（demo-16）

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <suiteXmlFiles>
            <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>org.apache.maven.surefire</groupId>
            <artifactId>surefire-testng</artifactId>
            <version>3.2.5</version>
        </dependency>
    </dependencies>
</plugin>
```

并且 `StrutsJUnit4TestCase`（JUnit 4 风格）依赖 `spring-web` 提供
`org.springframework.http.MediaType`，需要在 `pom.xml` 显式加：

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>6.2.19</version>
    <scope>test</scope>
</dependency>
```

---

## 9. 不兼容的旧插件

| 插件 | Struts 7.x 兼容版本 | 本仓库 demo 对策 |
|---|---|---|
| `struts2-oval-plugin` 6.10.0 | ❌（依赖被移除的 `MethodFilterInterceptor` + `fileUpload` 别名） | 改用自定义 `OValValidationInterceptor`（demo-28）|
| `struts2-sitemesh-plugin` | ❌（已移除）| demo-30 演示 sitemesh 3 直接集成 |

---

## 10. 文档来源

- [Struts 6.x.x to 7.x.x migration guide](https://cwiki.apache.org/confluence/x/tAljFQ)
- [Struts 7.0.0 GA Announcement](https://lists.apache.org/api/plain?thread=zspl5psb59h8mwnzmrr1w8ot73zp2348)
- [Struts Announcements](https://struts.apache.org/announce.html)
- [OpenRewrite Migrate to Struts 7.0](https://docs.openrewrite.org/recipes/java/struts/migrate7/migratestruts7)（自动迁移 Recipe，可用于 CI）

---

## 11. 一句话总结

> **JDK 17 + Jakarta Servlet 6 + 拦截器 camelCase 重命名 + OGNL/参数注入默认收紧 + 老 plugin 重写或换替代**。
>
> 大多数变更通过 IDE 的"包搜索替换 + interceptor name 重命名"完成；少数（oval / sitemesh）
> 需要写适配代码。