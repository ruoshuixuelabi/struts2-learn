package com.example.learn.struts2.demo34;

import org.apache.struts2.StrutsJUnit5Test;
import org.jfree.chart.JFreeChart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SalesPieChartActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testPieChart() throws Exception {
        String result = executeAction("/chart/pie.action");
        assertEquals("success", result);

        SalesPieChartAction action = (SalesPieChartAction) actionInvocation.getAction();
        JFreeChart chart = action.getChart();
        assertNotNull(chart);
        assertNotNull(chart.getTitle());
    }

    @Test
    public void testBarChart() throws Exception {
        String result = executeAction("/chart/bar.action");
        assertEquals("success", result);

        SalesBarChartAction action = (SalesBarChartAction) actionInvocation.getAction();
        assertNotNull(action.getChart());
    }

    @Test
    public void testLineChart() throws Exception {
        String result = executeAction("/chart/line.action");
        assertEquals("success", result);

        LineChartAction action = (LineChartAction) actionInvocation.getAction();
        assertNotNull(action.getChart());
    }
}