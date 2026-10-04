# legacy-demo-05-portlet

**主题**：Struts 2.5.30 JSR-168 Portlet 集成（struts2-portlet-plugin）

**状态**：**仅配置示例**（不要求 Web 容器跑通，Portlet 需要 Liferay / Pluto / WebSphere Portal）

## 插件用途

让 Struts 2 Action 在 JSR-168 / JSR-286 Portal 容器中运行：
- PortletAction 三阶段生命周期：render / action / resource
- Action 替代 Portlet 的 view / edit / help 模式
- 复用 Struts 拦截器、OGNL、Result 体系

## 弃用原因

1. **Portal 时代已过**：Web 化趋势使 Portal 容器使用率大幅下降
2. **用户群体小**：仅传统大型企业（银行、政府）还在用 WebSphere Portal
3. **架构过时**：Portlet 三阶段生命周期比 SPA + REST 复杂
4. **7.x 移除**

## 完整跑通所需基础设施

- Liferay 7.x / Apache Pluto 3.x / IBM WebSphere Portal
- Portlet API 2.0（JSR-286）
- 把 WAR 部署到 Portal 容器的 portlet 应用目录

## 迁移方案

- SPA（React/Vue）+ REST API：现代化 Web 架构
- Liferay 自带 MVC 框架：Liferay 平台原生方案
- 微前端（qiankun / single-spa）：门户式集成

## 编译验证

```bash
mvn compile  # 验证 Java 代码编译通过（Portal 运行时不启动）
```