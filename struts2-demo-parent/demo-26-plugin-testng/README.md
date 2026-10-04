# Demo 26: Plugin - TestNG (StrutsTestNGTestCase)

`struts2-testng-plugin` 演示：基于 `StrutsTestNGTestCase`（TestNG）的 Struts Action 单元测试，支持 `@DataProvider` 参数化测试。

## 启动

```bash
mvn -pl demo-26-plugin-testng test
```

## 文件清单

- `UserAction.java`：含 save / list 两种业务方法
- `UserActionTest.java`：继承 `StrutsTestNGTestCase`，含 `@DataProvider` 参数化测试
- `testng.xml`：TestNG 套件配置
- `struts.xml`：注册 action
- `web.xml`：注册 Struts 过滤器

## 关键代码

```java
public class UserActionTest extends StrutsTestNGTestCase {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    @Test
    public void testList() throws Exception {
        request.setParameter("page", "1");
        assertEquals(executeAction("/user!list.action"), "success");
    }

    @DataProvider(name = "users")
    public Object[][] createData() {
        return new Object[][] {
            { "alice", "alice@example.com", "success" },
            { "bob",   "bob@example.com",   "success" },
            { "",      "no-name@example.com", "input" }
        };
    }

    @Test(dataProvider = "users")
    public void testSave(String username, String email, String expected) throws Exception {
        request.setParameter("user.username", username);
        request.setParameter("user.email", email);
        String result = executeAction("/user!save.action");
        assertEquals(result, expected);
    }
}
```

## 分组运行

```bash
mvn test -Dgroups=validation
mvn test -DexcludedGroups=slow
```

## 对应文档

参见 `struts2-learn/28-插件-TestNG.md`
