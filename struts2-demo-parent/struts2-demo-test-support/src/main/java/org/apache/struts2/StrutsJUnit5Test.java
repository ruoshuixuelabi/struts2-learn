package org.apache.struts2;

import jakarta.servlet.ServletException;
import org.apache.struts2.junit.StrutsJUnit4TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.UnsupportedEncodingException;

/**
 * JUnit 5 友好的 Struts2 Action 测试基类（支持泛型指定 Action 类型）。
 *
 * <h2>为什么需要这个类</h2>
 * <p>官方 {@code struts2-junit-plugin} 7.3.0 只提供 JUnit 4 风格的基类
 * （{@code StrutsJUnit4TestCase}），且其 {@code setUp()} / {@code tearDown()}
 * 标注的是 Struts2 自有注解（{@code org.apache.struts2.interceptor.annotations.Before}），
 * 并<strong>不会</strong>被 JUnit 5 / JUnit 4 自动调用——开发者必须手动调用。</p>
 *
 * <p>本类包一层 JUnit 5 生命周期（{@code @BeforeEach} / {@code @AfterEach}），
 * 让子类可以直接写 JUnit 5 {@code @Test} 方法，setUp / tearDown 自动跑起来。</p>
 *
 * <h2>泛型支持</h2>
 * <p>子类可以用 {@code extends StrutsJUnit5Test<MyAction>} 声明 Action 类型，
 * 然后直接调用 {@link #getAction()}（无需强转）。</p>
 *
 * <h2>对外暴露的 API</h2>
 * <ul>
 *   <li>{@link #executeAction(String)} — 执行 action，返回结果码（"success" 等）</li>
 *   <li>{@link #actionInvocation} — 当前 {@code ActionInvocation}，可调 {@code getAction()} 拿 Action 实例</li>
 *   <li>{@link #getAction()} — 返回本次执行的 Action 实例（类型为泛型 T）</li>
 *   <li>{@link #getActionProxy(String)} — 拿到 {@code ActionProxy}（更细粒度控制）</li>
 *   <li>{@link #getConfigPath()} — 子类覆盖，指定 struts.xml 路径</li>
 * </ul>
 *
 * <h2>典型用法</h2>
 * <pre>{@code
 * public class HelloActionTest extends StrutsJUnit5Test<HelloAction> {
 *     @Override
 *     protected String getConfigPath() { return "struts.xml"; }
 *
 *     @Test
 *     public void testHello() throws Exception {
 *         String result = executeAction("/hello.action");
 *         assertEquals("success", result);
 *         HelloAction action = getAction();
 *         assertEquals("Hello!", action.getMessage());
 *     }
 * }
 * }</pre>
 *
 * <h2>限制</h2>
 * <ul>
 *   <li>父类的 {@code setUp} / {@code tearDown} 标注的是 Struts 2 的 {@code @Before} / {@code @After}，
 *       本类改用 JUnit 5 的 {@code @BeforeEach} / {@code @AfterEach} 包一层。</li>
 *   <li>由于 Mock servlet 对象基于 Spring 的 mock 实现，部分 servlet 容器特性（如 async-supported）
 *       不能完整模拟，需要时直接调 Jetty 跑集成测试。</li>
 * </ul>
 *
 * @param <T> 被测 Action 的类型（必须实现 {@link org.apache.struts2.action.Action}）
 */
public abstract class StrutsJUnit5Test<T extends org.apache.struts2.action.Action> extends StrutsJUnit4TestCase {

    /** 当前 {@link #executeAction(String)} 调用产生的 {@link ActionInvocation} */
    protected ActionInvocation actionInvocation;

    @BeforeEach
    public void setUpJUnit5() throws Exception {
        // 父类 setUp() 在 StrutsJUnit4TestCase 里标注的是 Struts2 自己的 @Before，
        // JUnit 5 不会自动调用，必须在这里手动调用。
        // 覆盖整个 setUp 流程是为了在 dispatcher initParams 里加上 struts-default.xml 和
        // struts-plugin.xml——这样 dispatcher init 时会注册它们的 <bean> 和 package。
        // 修复 StrutsJUnit4TestCase 默认只加载 struts.xml 导致 JSONUtil 等 bean 没注册。
        initServletMockObjects();
        setupBeforeInitDispatcher();
        initDispatcherParams();
        // 父类 initDispatcherParams() 已经把 config 设为 "struts-default.xml," + getConfigPath()
        // （字节码常量池 struts-default.xml,），但没有加 struts-plugin.xml。
        // 如果 config 里没有 struts-plugin.xml，则在 struts-default.xml 之后、struts.xml 之前插入。
        // 注意：struts-plugin.xml 里 plugin package extends="struts-default"，必须 struts-default.xml 先加载。
        Object originalConfigObj = this.dispatcherInitParams.get("config");
        String originalConfig = (originalConfigObj instanceof String) ? (String) originalConfigObj : null;
        if (originalConfig != null && !originalConfig.contains("struts-plugin.xml")) {
            // 在第一个逗号后插入 "struts-plugin.xml,"（保留 struts-default.xml 在前）
            int idx = originalConfig.indexOf(',');
            String merged;
            if (idx < 0) {
                merged = originalConfig + ",struts-plugin.xml";
            } else {
                merged = originalConfig.substring(0, idx + 1) + "struts-plugin.xml," + originalConfig.substring(idx + 1);
            }
            this.dispatcherInitParams.put("config", merged);
        }
        // 关闭 Struts 2.7 引入的 requireAnnotations 默认检查
        // （默认行为：所有 setter 必须加 @StrutsParameter 注解才会被自动注入）。
        // 我们 demo 用的是传统 setter，关闭检查才能 setId / setName 等生效。
        // 子类 struts.xml 中如果显式设置此常量会覆盖这里（这是 desired behavior）。
        // 注意 key 是 struts.parameters.requireAnnotations（来自 org/apache/struts2/default.properties）。
        this.dispatcherInitParams.putIfAbsent("struts.parameters.requireAnnotations", "false");
        System.err.println("[StrutsJUnit5Test] dispatcherInitParams.config = " + this.dispatcherInitParams.get("config"));
        initDispatcher(dispatcherInitParams);
        // 加载 classpath 上所有 struts-plugin.xml（注册 plugin 提供的 bean 和 package，
        // 例如 json-default / struts-bean-validation / spring-default 等）。
        loadAllStrutsPluginXmls();
    }

    /**
     * 让子类测试在 initDispatcher 之后、executeAction 之前调用。
     * 扫描所有 struts-plugin.xml 文件，逐个作为 ContainerProvider 加到 ConfigurationManager，
     * 并 reload() 一次以注册 plugin xml 里的 <bean> 和 package。
     *
     * <p>背景：StrutsJUnit4TestCase 默认只把 "struts.xml" 放进 dispatcherInitParams["config"]，
     * 跳过 struts-plugin.xml。即使我们手动把 "struts-plugin.xml" 加进 config 字符串，
     * PluginConfigurationProvider 的 plugin xml 加载顺序也可能因为容器尚未初始化而被忽略。</p>
     */
    protected void loadAllStrutsPluginXmls() {
        if (this.configurationManager == null) {
            throw new IllegalStateException("call loadAllStrutsPluginXmls() AFTER initDispatcher() (i.e. after setUpJUnit5)");
        }
        boolean added = false;
        int urlsSeen = 0;
        // 拿到 dispatcher 内部已经构造好的 FileManager（XmlConfigurationProvider 需要它）。
        org.apache.struts2.FileManager fileManager =
            this.configurationManager.getConfiguration() == null
                ? null
                : (org.apache.struts2.FileManager) this.container.getInstance(org.apache.struts2.FileManager.class);
        try {
            java.util.Enumeration<java.net.URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources("struts-plugin.xml");
            while (urls.hasMoreElements()) {
                java.net.URL url = urls.nextElement();
                urlsSeen++;
                org.apache.struts2.config.providers.XmlConfigurationProvider provider =
                    new org.apache.struts2.config.providers.XmlConfigurationProvider("struts-plugin.xml") {
                        @Override
                        protected java.util.Iterator<java.net.URL> getConfigurationUrls(String fileName) throws java.io.IOException {
                            // 单文件指向具体 URL
                            return java.util.Collections.singletonList(url).iterator();
                        }
                    };
                if (fileManager != null) {
                    // XmlConfigurationProvider 的 fileManager 字段是 protected，
                    // 没有 setter。用 reflection 直接设值。
                    try {
                        java.lang.reflect.Field fmField = org.apache.struts2.config.providers.XmlConfigurationProvider.class
                            .getDeclaredField("fileManager");
                        fmField.setAccessible(true);
                        fmField.set(provider, fileManager);
                    } catch (ReflectiveOperationException roe) {
                        // ignore — provider 会在加载 xml 时报 fileManager null
                    }
                }
                // init
                provider.init(this.configurationManager.getConfiguration());
                this.configurationManager.addContainerProvider(provider);
                added = true;
            }
        } catch (java.io.IOException e) {
            // ignore
        }
        if (added) {
            this.configurationManager.reload();
            System.err.println("[StrutsJUnit5Test] reloaded config with "
                + urlsSeen + " plugin xml files");
        }
    }

    /**
     * 覆盖父类 initActionContext：除了把 request 的 parameters 同步到 ActionContext 之外，
     * <strong>每次都重新从当前 this.request 重新读 parameters</strong>，避免覆盖：
     * 测试方法在 setUpJUnit5 之后才调 request.setParameter(...)——那时 initActionContext
     * 还没跑过；getActionProxy 内部会调 initActionContext，但如果子类（比如我们的 StrutsJUnit5Test
     * executeAction）提前用 ActionContext.getContext() 设置过东西，super.initActionContext
     * 会用旧的 HttpParameters 创建方法覆盖。
     * <p>实现：在 super.initActionContext 之前先把当前 this.request 的 parameters 写入
     * ActionContext（即使 super 后面会再读一次，至少保证读到最新值——super 也是从 this.request 读的，
     * 所以两者一致）。</p>
     */
    @Override
    protected void initActionContext(org.apache.struts2.ActionContext context) {
        // 先把当前 request 的 parameters 写入 context，再调 super 让它再次写入（幂等）。
        org.apache.struts2.dispatcher.HttpParameters params =
            org.apache.struts2.dispatcher.HttpParameters.create(this.request.getParameterMap()).build();
        context.withParameters(params);
        super.initActionContext(context);
    }

    /**
     * 把 classpath 上存在的 Struts 插件 BeanSelectionProvider 加到 dispatcher 的
     * configurationManager，调用 reload() 让 container 重新构建。
     * 类不存在时跳过（软依赖）。
     */
    private void addAndReloadOptionalProviders() {
        if (this.dispatcher == null) return;
        org.apache.struts2.config.ConfigurationManager cm =
            this.dispatcher.getConfigurationManager();
        if (cm == null) return;
        boolean added = false;
        added |= addOneProviderIfPresent(cm, "org.apache.struts2.json.JSONBeanSelectionProvider");
        added |= addOneProviderIfPresent(cm, "org.apache.struts2.rest.RestBeanSelectionProvider");
        added |= addOneProviderIfPresent(cm, "org.apache.struts2.convention.DefaultBeanSelectionProvider");
        added |= addOneProviderIfPresent(cm, "org.apache.struts2.oval.OValConfigurationProvider");
        added |= addOneProviderIfPresent(cm, "org.apache.struts2.sitemesh.SitemeshConfigurationProvider");
        if (added) {
            // 触发 container 重新构建，让新加的 BeanSelectionProvider 生效
            cm.reload();
        }
    }

    private boolean addOneProviderIfPresent(org.apache.struts2.config.ConfigurationManager cm, String className) {
        try {
            Class<?> cls = Class.forName(className);
            Object provider = cls.getDeclaredConstructor().newInstance();
            if (provider instanceof org.apache.struts2.config.ContainerProvider) {
                cm.addContainerProvider((org.apache.struts2.config.ContainerProvider) provider);
                return true;
            }
        } catch (ClassNotFoundException e) {
            // classpath 上没有这个插件，跳过
        } catch (Exception e) {
            System.err.println("[" + className + "] 加载失败: " + e.getMessage());
        }
        return false;
    }

    @AfterEach
    public void tearDownJUnit5() throws Exception {
        super.tearDown();
        this.actionInvocation = null;
    }

    /**
     * 执行 Action 并把 {@link ActionInvocation} 缓存到 {@link #actionInvocation} 字段。
     *
     * <p>覆盖父类实现——父类通过 {@code Dispatcher.serviceAction()} 走完整流程
     * （包含 result 渲染），返回的是响应 body 字符串；本类用 {@link #getActionProxy(String)}
     * 拿 ActionProxy 再 execute，能访问 invocation，方便子类断言 action 状态。</p>
     */
    @Override
    protected String executeAction(String uri) throws ServletException, UnsupportedEncodingException {
        // 关键修复：Struts 7.3.0 的 StrutsJUnit4TestCase.getActionProxy() 内部用
        // MockHttpServletRequest.setRequestURI(uri) 设置 URI，再调 getActionMapping(request)
        // 解析。如果 uri 包含 query string (?username=...)，解析会失败。
        // 解决方案：在调 getActionProxy 前把 query string 剥掉，参数后面通过 request.setParameter 单独传。
        String uriOnly = uri;
        int qIdx = uri.indexOf('?');
        String query = null;
        if (qIdx >= 0) {
            uriOnly = uri.substring(0, qIdx);
            query = uri.substring(qIdx + 1);
        }
        if (query != null) {
            for (String pair : query.split("&")) {
                int eq = pair.indexOf('=');
                String key = eq < 0 ? pair : pair.substring(0, eq);
                String val = eq < 0 ? "" : pair.substring(eq + 1);
                this.request.setParameter(key, java.net.URLDecoder.decode(val, "UTF-8"));
            }
        }
        // 关键修复：Struts 7.3.0 的 StrutsJUnit4TestCase.getActionProxy() 在
        // ActionProxyFactory.createActionProxy() 之后才把 request 绑定到
        // ServletActionContext；但 createActionProxy() 内部 prepare() 调
        // getErrorMessage() 时就会 ServletActionContext.getRequest().getContextPath()，
        // 此时 thread-local 还是 null，触发 NPE。
        // 解决方案：在调 getActionProxy 前显式 setRequest 一次。
        org.apache.struts2.ServletActionContext.setRequest(this.request);
        org.apache.struts2.ServletActionContext.setResponse(this.response);

        org.apache.struts2.ActionProxy proxy = getActionProxy(uriOnly);
        String result;
        try {
            result = proxy.execute();
        } catch (Exception e) {
            // ActionProxy.execute() 声明 throws Exception，子类契约只允许
            // ServletException / UnsupportedEncodingException，所以内部吞掉并转 ServletException
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            throw new ServletException("Action execution failed: " + cause.getMessage(), cause);
        }
        this.actionInvocation = proxy.getInvocation();
        return result;
    }

    /**
     * 返回本次 {@link #executeAction(String)} 调用产生的 Action 实例（泛型 T）。
     * <p>调用前必须先调一次 {@link #executeAction(String)}，否则 {@link #actionInvocation} 为 null。</p>
     */
    @SuppressWarnings("unchecked")
    public T getAction() {
        if (actionInvocation == null) {
            throw new IllegalStateException("getAction() 必须在 executeAction() 之后调用");
        }
        return (T) actionInvocation.getAction();
    }
}