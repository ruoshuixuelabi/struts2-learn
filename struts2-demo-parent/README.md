# Struts 2 Demo Parent（Struts 7.3.0 GA）

**当前版本学习项目**，覆盖 Struts 7.3.0 GA + JDK 17 + Jakarta Servlet 6。

## 包含 36 个子模块

按学习阶段分组（详见 spec §4）：
- **入门**（demo-01）：Hello World
- **核心**（demo-02~08）：架构、Action、配置、拦截器、值栈、OGNL、标签
- **扩展**（demo-09~18）：i18n、异常、上传、结果、验证、测试
- **生态**（demo-19~20）：Spring 集成、Security
- **插件**（demo-21~36）：每个官方插件 + 自定义插件

> ⚠️ **从 Struts 6.x 升级到 7.x 的变更**（拦截器重命名 / 包迁移 / OGNL 默认值收紧等），
> 详见 [STRUTS-7-MIGRATION.md](STRUTS-7-MIGRATION.md)。

## 编译与运行

```bash
# 编译整个父项目（仅打包，不启动）
mvn install -DskipTests

# 运行某个 demo（使用嵌入式 Jetty）
mvn -pl demo-01-helloworld jetty:run

# 跑全部 demo 的单元测试
mvn test
```

## 启动步骤

每个 demo 都是独立的 Web 应用。访问 `http://localhost:8080/<demo-name>/` 测试。

具体每个 demo 的入口 URL 在各自的 README.md 中说明（后续 plan 添加）。