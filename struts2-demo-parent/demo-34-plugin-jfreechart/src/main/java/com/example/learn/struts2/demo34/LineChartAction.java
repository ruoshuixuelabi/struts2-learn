package com.example.learn.struts2.demo34;

import org.apache.struts2.ActionSupport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * demo-34: JFreeChart 插件演示 —— 折线图。
 */
public class LineChartAction extends ActionSupport {

    private JFreeChart chart;

    @Override
    public String execute() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int day = 1; day <= 7; day++) {
            int v = 50 + (int) (Math.random() * 50);
            dataset.addValue(v, "PV", "D" + day);
        }

        chart = ChartFactory.createLineChart(
            "周访问量趋势（demo-34）",
            "日期",
            "PV",
            dataset
        );
        return SUCCESS;
    }

    public JFreeChart getChart() {
        return chart;
    }
}