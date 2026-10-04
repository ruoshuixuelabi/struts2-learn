package com.example.learn.struts2.demo33;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.struts2.ActionSupport;

import java.io.InputStream;

/**
 * demo-33 进阶：运行时编译 .jrxml 并暴露 JasperPrint。
 * <p>
 * jasper result 看到 {@code getJasperPrint()} 时直接使用，不再走 dataSource 注入路径。
 */
public class DynamicReportAction extends ActionSupport {

    private JasperPrint jasperPrint;

    /** plainText result 读取的占位属性（仅用于绕过 jasper result 的 JRLoader 路径） */
    private final String renderHint = "dynamic-jasperprint-ready";

    @Override
    public String execute() throws Exception {
        // 运行时编译 jrxml（也支持预编译为 .jasper 后用 JasperFillManager 加载）
        try (InputStream template = getClass().getResourceAsStream("/jasper/dynamic.jrxml")) {
            JasperReport report = JasperCompileManager.compileReport(template);

            // 用 UserReportAction.User 列表填充（也可改 JREmptyDataSource 做空模板演示）
            JRDataSource dataSource = new JRBeanCollectionDataSource(sampleUsers());
            jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
        }
        return SUCCESS;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public String getRenderHint() {
        return renderHint;
    }

    private static java.util.List<UserReportAction.User> sampleUsers() {
        java.util.List<UserReportAction.User> list = new java.util.ArrayList<>();
        list.add(new UserReportAction.User(1L, "运行时", "rt@example.com", "动态"));
        return list;
    }

    /** 让 JREmptyDataSource 引用保留以避免静态分析删除 import（用于 dynamic.jrxml 的 detail-only 场景） */
    @SuppressWarnings("unused")
    private static JREmptyDataSource emptyDs() {
        return new JREmptyDataSource();
    }
}