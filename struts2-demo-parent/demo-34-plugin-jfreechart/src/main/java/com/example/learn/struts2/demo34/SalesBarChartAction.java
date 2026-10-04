package com.example.learn.struts2.demo34;

import org.apache.struts2.ActionSupport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * demo-34: JFreeChart 插件演示 —— 柱状图（按月份）。
 */
public class SalesBarChartAction extends ActionSupport {

    private JFreeChart chart;

    @Override
    public String execute() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(120, "2024", "1月");
        dataset.addValue( 95, "2024", "2月");
        dataset.addValue(180, "2024", "3月");
        dataset.addValue(160, "2024", "4月");
        dataset.addValue(220, "2024", "5月");

        dataset.addValue(140, "2025", "1月");
        dataset.addValue(165, "2025", "2月");
        dataset.addValue(210, "2025", "3月");
        dataset.addValue(190, "2025", "4月");
        dataset.addValue(260, "2025", "5月");

        chart = ChartFactory.createBarChart(
            "季度销量对比（demo-34）",
            "月份",
            "销量",
            dataset,
            PlotOrientation.VERTICAL,
            true,   // legend
            true,   // tooltips
            false   // urls
        );
        return SUCCESS;
    }

    public JFreeChart getChart() {
        return chart;
    }
}