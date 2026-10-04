# Demo 25: Plugin - JUnit (StrutsJUnit4TestCase)

`struts2-junit-plugin` 演示：基于 `StrutsJUnit4TestCase`（JUnit 4）的 Struts Action 单元测试。

## 启动

```bash
mvn -pl demo-25-plugin-junit test
```

## 文件清单

- `HelloAction.java`：简单 Action，含 setter/getter
- `HelloActionTest.java`：继承 `StrutsJUnit4TestCase`，用 `executeAction()` 模拟请求
- `struts.xml`：注册 `hello` action
- `web.xml`：注册 Struts 过滤器

## 关键代码

```java
public class HelloActionTest extends StrutsJUnit4TestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testHello() throws Exception {
        request.setParameter("user.name", "Alice");
        String result = executeAction("/hello.action");
        assertEquals("success", result);
    }
}
```

## StrutsJUnit4TestCase vs StrutsJUnit5Test

| 基类 | 测试框架 | 特点 |
|---|---|---|
| `StrutsJUnit4TestCase` | JUnit 4 | 继承 `junit.framework.TestCase` 兼容；老项目 |
| `StrutsJUnit5Test` | JUnit 5 | 不继承 TestCase；轻量；新项目推荐 |

## 对应文档

参见 `struts2-learn/27-插件-JUnit.md`
