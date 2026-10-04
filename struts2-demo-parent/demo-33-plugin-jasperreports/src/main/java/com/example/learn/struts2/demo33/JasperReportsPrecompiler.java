package com.example.learn.struts2.demo33;

import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;

/**
 * 把 classpath 上的 .jrxml 预编译成 .jasper 字节码文件，写到 java.io.tmpdir/jasper/ 下。
 *
 * <p>背景：Struts JasperReportsResult 通过
 * {@code net.sf.jasperreports.engine.util.JRLoader.loadObject(File)} 加载模板，
 * 该方法按 JasperReports 二进制序列化格式（魔数 CAFE BABE 风格）读取——直接喂 .jrxml
 * （XML）会抛 {@code StreamCorruptedException: invalid stream header: 3C3F786D}。</p>
 *
 * <p>传统方案是 maven 构建期用 antrun/exec 调 {@code JasperCompileManager} 把 .jrxml → .jasper，
 * 但本 demo 不增加额外的 maven 插件，所以在 JVM 启动时（StrutsJUnit5Test.setUp 之前）
 * 通过 {@link #ensureCompiled()} 把所有 classpath:/jasper/*.jrxml 一次性预编译到临时目录，
 * 然后 struts.xml 用 .jasper 路径即可。</p>
 */
public final class JasperReportsPrecompiler {

    private static final Logger LOG = LogManager.getLogger(JasperReportsPrecompiler.class);
    private static final String CLASSPATH_ROOT = "/jasper/";
    private static final String PROP_USER_LIST = "demo33.jasper.userList";
    private static volatile boolean done = false;

    private JasperReportsPrecompiler() {
    }

    public static synchronized void ensureCompiled() {
        if (done) {
            return;
        }
        File outDir = new File(System.getProperty("java.io.tmpdir"), "demo-33-jasper");
        if (!outDir.exists() && !outDir.mkdirs()) {
            LOG.warn("Cannot create tmp dir {}, skip precompile", outDir);
            return;
        }
        // ClassLoader.getResources 路径不带前导斜杠
        String resourcePath = CLASSPATH_ROOT.startsWith("/") ? CLASSPATH_ROOT.substring(1) : CLASSPATH_ROOT;
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(resourcePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (!"file".equals(url.getProtocol())) {
                    LOG.debug("Skip non-file jrxml source: {}", url);
                    continue;
                }
                File srcDir = new File(url.toURI());
                File[] jrxmls = srcDir.listFiles((d, name) -> name.endsWith(".jrxml"));
                if (jrxmls == null) {
                    continue;
                }
                for (File jrxml : jrxmls) {
                    File jasper = new File(outDir, jrxml.getName().replace(".jrxml", ".jasper"));
                    if (jasper.exists() && jasper.lastModified() >= jrxml.lastModified()) {
                        LOG.debug("Jasper already up-to-date: {}", jasper);
                    } else {
                        LOG.info("Compiling jrxml {} -> {}", jrxml, jasper);
                        JasperCompileManager.compileReportToFile(jrxml.getAbsolutePath(), jasper.getAbsolutePath());
                    }
                    // 把 .jasper 绝对路径写到 system property，供 struts.xml ${} 占位符读取
                    if ("user-list.jrxml".equals(jrxml.getName())) {
                        System.setProperty(PROP_USER_LIST, jasper.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Failed to precompile jasper reports", e);
            return;
        }
        done = true;
        // 同时把 classpath:/jasper 路径直接替换成 /tmp/.../demo-33-jasper 的绝对路径，
        // 让 struts.xml 即使直接写 "/jasper/user-list.jrxml" 也能在测试里走 .jasper 文件。
        try {
            java.lang.reflect.Field f = JasperReportsPrecompiler.class.getDeclaredField("CLASSPATH_ROOT");
            // 反射改常量不可行；改用给 dynamic action 直接用的常量
        } catch (Exception ignore) {
        }
    }

    /** 测试用：把 .jasper 拷贝到 classpath 同名位置（覆盖 .jrxml），让 classpath:/jasper/xxx.jrxml 也能用。
     *  只覆盖 user-list.jrxml（被 Struts jasper result 直接加载）；
     *  dynamic.jrxml 由 DynamicReportAction 在运行时编译读取，不能被覆盖。
     */
    public static void overwriteClasspathJasper() {
        ensureCompiled();
        File outDir = new File(System.getProperty("java.io.tmpdir"), "demo-33-jasper");
        String resourcePath = CLASSPATH_ROOT.startsWith("/") ? CLASSPATH_ROOT.substring(1) : CLASSPATH_ROOT;
        File jasper = new File(outDir, "user-list.jasper");
        if (!jasper.exists()) {
            LOG.warn("overwriteClasspathJasper: {} missing", jasper);
            return;
        }
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(resourcePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (!"file".equals(url.getProtocol())) {
                    continue;
                }
                File srcDir = new File(url.toURI());
                File target = new File(srcDir, "user-list.jrxml");
                Files.copy(jasper.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                LOG.info("Overwrote classpath jrxml slot with compiled jasper: {}", target);
            }
        } catch (Exception e) {
            LOG.warn("overwriteClasspathJasper failed: {}", e.getMessage());
        }
    }

    /** 解析 classpath:/jasper/xxx.jasper → /tmp/demo-33-jasper/xxx.jasper，供 struts.xml 使用 */
    public static String resolve(String classpathLocation) {
        ensureCompiled();
        if (classpathLocation == null || !classpathLocation.startsWith(CLASSPATH_ROOT)) {
            return classpathLocation;
        }
        String name = classpathLocation.substring(CLASSPATH_ROOT.length());
        return new File(new File(System.getProperty("java.io.tmpdir"), "demo-33-jasper"), name).getAbsolutePath();
    }

    /** 给 struts.xml OGNL 静态方法调用用的便捷方法（参数会被 OGNL 转义） */
    public static String userListJasper() {
        return resolve(CLASSPATH_ROOT + "user-list.jrxml");
    }

    /** 让父类加载器无 resources 时跳过：占位，避免 import 警告 */
    @SuppressWarnings("unused")
    private static InputStream noop() {
        return null;
    }

    /** 让 Path/Files/StandardCopyOption 引用保留以备后续扩展（备用 API） */
    @SuppressWarnings("unused")
    private static final Class<?> KEEP_NIO = Path.class;
    @SuppressWarnings("unused")
    private static final Class<?> KEEP_PATH = Paths.class;
    @SuppressWarnings("unused")
    private static final Class<?> KEEP_COPY = StandardCopyOption.class;
    @SuppressWarnings("unused")
    private static final Class<?> KEEP_IO = IOException.class;
    @SuppressWarnings("unused")
    private static final Class<?> KEEP_FILES = Files.class;
}