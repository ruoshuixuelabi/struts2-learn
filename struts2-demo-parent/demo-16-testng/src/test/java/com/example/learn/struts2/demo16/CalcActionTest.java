package com.example.learn.struts2.demo16;

import org.apache.struts2.StrutsJUnit5Test;
import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * TestNG + Struts 测试基类。
 * <p>
 * 故意不用 struts2-junit-plugin 自带的 {@code StrutsJUnit4TestCase}，因为它内部
 * {@code getActionProxy(uri)} 直接把 query string 拼到 request URI 里，
 * 导致 actionMapping 查不到（Struts 7.x 严格匹配 URI，query 必须剥离）。
 * {@link StrutsJUnit5Test}（来自 struts2-demo-test-support）已经做了 query-string
 * 剥离和参数注入，能正确工作。
 */
public class CalcActionTest extends StrutsJUnit5Test<CalcAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @BeforeMethod
    public void setUp() throws Exception {
        // StrutsJUnit5Test 用 JUnit 5 的 @BeforeEach；这里手动调一次等价逻辑。
        // 复刻父类的 setupBeforeInitDispatcher → initDispatcherParams → initDispatcher 流程。
        initServletMockObjects();
        setupBeforeInitDispatcher();
        initDispatcherParams();
        Object originalConfigObj = this.dispatcherInitParams.get("config");
        String originalConfig = (originalConfigObj instanceof String) ? (String) originalConfigObj : null;
        if (originalConfig != null && !originalConfig.contains("struts-plugin.xml")) {
            int idx = originalConfig.indexOf(',');
            String merged;
            if (idx < 0) {
                merged = originalConfig + ",struts-plugin.xml";
            } else {
                merged = originalConfig.substring(0, idx + 1) + "struts-plugin.xml," + originalConfig.substring(idx + 1);
            }
            this.dispatcherInitParams.put("config", merged);
        }
        this.dispatcherInitParams.putIfAbsent("struts.parameters.requireAnnotations", "false");
        initDispatcher(this.dispatcherInitParams);
        loadAllStrutsPluginXmls();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * 普通成功用例：1 + 2 = 3
     */
    @Test(groups = "happy")
    public void testAdd() throws Exception {
        request.setParameter("a", "1");
        request.setParameter("b", "2");
        request.setParameter("op", "add");

        String result = executeAction("/calc.action");

        assertEquals(result, "success");
        CalcAction action = getAction();
        assertEquals(action.getResult(), Integer.valueOf(3));
    }

    /**
     * 边界：除以 0 → input + actionError
     */
    @Test(groups = "validation")
    public void testDivByZero() throws Exception {
        request.setParameter("a", "10");
        request.setParameter("b", "0");
        request.setParameter("op", "div");

        String result = executeAction("/calc.action");

        assertEquals(result, "input");
        CalcAction action = getAction();
        assertTrue(action.hasActionErrors());
    }

    /**
     * 非法 op
     */
    @Test(groups = "validation")
    public void testInvalidOp() throws Exception {
        request.setParameter("a", "1");
        request.setParameter("b", "1");
        request.setParameter("op", "pow");

        String result = executeAction("/calc.action");

        assertEquals(result, "input");
    }

    /**
     * @DataProvider 参数化：覆盖四种运算符
     */
    @DataProvider(name = "calcCases")
    public Object[][] calcCases() {
        return new Object[][] {
            { 6, 2, "add", 8 },
            { 6, 2, "sub", 4 },
            { 6, 2, "mul", 12 },
            { 6, 2, "div", 3 }
        };
    }

    @Test(dataProvider = "calcCases", groups = "happy")
    public void testCalcParameterized(int a, int b, String op, int expected) throws Exception {
        request.setParameter("a", String.valueOf(a));
        request.setParameter("b", String.valueOf(b));
        request.setParameter("op", op);

        String result = executeAction("/calc.action");

        assertEquals(result, "success");
        CalcAction action = getAction();
        assertEquals(action.getResult(), Integer.valueOf(expected));
    }
}