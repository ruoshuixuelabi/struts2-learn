# legacy-demo-03-osgi

**主题**：Struts 2.5.30 OSGi 集成（struts2-osgi-plugin）

**状态**：**仅配置示例**（不要求 Web 容器跑通，OSGi 需要 Felix/Equinox 容器）

## 插件用途

让 Struts 2 Action 作为 OSGi bundle 部署：
- Action 通过 OSGi 服务注册中心发现其他服务
- 支持 Action 热部署
- Struts 框架本身作为 OSGi bundle 运行

## 弃用原因

1. **S2-048 漏洞**（CVE-2017-9793）：OGNL 注入 RCE
2. **维护成本**：OSGi 与 Struts 集成复杂
3. **用户群体小**：ROI 低
4. **7.x 移除**

## 配置示例

`struts.xml` 关键配置：

```xml
<constant name="struts.osgi" value="true"/>
<constant name="struts.osgi.host" value="felix"/>
<constant name="struts.osgi.enableBundleClassLoader" value="true"/>
```

## 完整跑通所需基础设施

- Apache Felix 或 Equinox OSGi 容器
- Spring OSGi / Gemini Blueprint
- 通过 felix:run 插件启动

## 迁移方案

- Spring Boot `@ConditionalOnClass`：按 classpath 决定 Bean 注册
- Java 9+ JPMS：JVM 级模块化
- OSGi 独立项目：脱离 Struts

## 编译验证

```bash
mvn compile  # 验证 Java 代码编译通过（OSGi 运行时不启动）
```