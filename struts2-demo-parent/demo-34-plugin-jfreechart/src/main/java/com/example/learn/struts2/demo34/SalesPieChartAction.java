package com.example.learn.struts2.demo34;

import org.apache.struts2.ActionSupport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * demo-34: JFreeChart 插件演示 —— 饼图。
 * <p>
 * Action 构造 JFreeChart 对象并通过 {@code getChart()} 暴露给 chart result，
 * 后者会自动渲染为 {@code image/png} 流。
 */
public class SalesPieChartAction extends ActionSupport {

    private JFreeChart chart;

    @Override
    public String execute() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("苹果", 25);
        dataset.setValue("橘子", 35);
        dataset.setValue("香蕉", 40);
        dataset.setValue("葡萄", 18);
        dataset.setValue("西瓜", 12);

        chart = ChartFactory.createPieChart(
            "水果销量占比（demo-34）",
            dataset,
            true,   // legend
            true,   // tooltips
            false   // urls
        );
        return SUCCESS;
    }

    /** chart result 会自动通过 OGNL 调用本 getter。 */
    public JFreeChart getChart() {
        return chart;
    }
}