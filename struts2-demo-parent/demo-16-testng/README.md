# Demo 16: TestNG 单元测试（StrutsTestCase）

演示 Struts + TestNG 测试方案：`StrutsTestCase`（TestNG 版）+ `@BeforeMethod` + `@DataProvider` 参数化。

## 启动

```bash
mvn -pl demo-16-testng jetty:run
```

访问：http://localhost:8080/demo-16-testng/calc.action

## 运行测试

```bash
mvn -pl demo-16-testng test
```

或指定分组：
```bash
mvn -pl demo-16-testng test -Dgroups=validation
```

## 文件清单

- `CalcAction.java`：计算 Action（接收 `a` / `b` / `op`，返回计算结果）
- `struts.xml`：`calc` action 映射 + `success` / `input` 两个 result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `calc.jsp`：显示 `${result}`
- `calc-form.jsp`：提交 `a` / `b` / `op` 的表单
- `CalcActionTest.java`：TestNG 单测（@Test + @DataProvider + groups）
- `testng.xml`：TestNG 套件配置

## 关键点

- **基类**：`org.apache.struts2.StrutsTestCase`（TestNG 版，与 JUnit 4 用法同）
- **`@BeforeMethod`**：必须调 `super.setUp()` 初始化 mock servlet 环境
- **`@AfterMethod`**：调 `super.tearDown()` 清理
- **`@DataProvider`**：参数化数据驱动（TestNG 内置，比 JUnit 5 `@ParameterizedTest` 直观）
- **`@Test(groups=...)`**：测试分组（CI 按 group 跑）
- **Surefire 配置**：必须显式配 `<suiteXmlFiles>` 指向 `testng.xml`

## 对应文档

- `struts2-learn/17-单元测试（JUnit+TestNG）.md`
- `struts2-learn/28-插件-TestNG.md`