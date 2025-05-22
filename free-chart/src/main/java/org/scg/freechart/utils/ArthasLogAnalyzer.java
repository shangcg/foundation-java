package org.scg.freechart.utils;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/5/22
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArthasLogAnalyzer {

    public static void main(String[] args) {
        String logData = "---[206.940331ms] com.didiglobal.kefu.fitan.web.biz.sopengine.controller.FlowExecuteController:executeFlow()\n" +
                "    +---[99.98% 206.904145ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.service.FlowExecuteService:execute() #38\n" +
                "    |   +---[6.54% 13.528437ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.service.impl.FlowExecuteServiceImpl:prepareFlowExecuteContext()\n" +
                "    |   |   +---[0.03% 0.004133ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:<init>() #99";

        DefaultCategoryDataset dataset = parseLogData(logData);
        JFreeChart chart = ChartFactory.createBarChart(
                "Arthas Method Cost Analysis",
                "Methods",
                "Cost (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static DefaultCategoryDataset parseLogData(String logData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Pattern pattern = Pattern.compile("\\[(\\d+\\.?\\d*)ms\\].*(.*):\\w+\\(.*\\)");
        Matcher matcher = pattern.matcher(logData);

        while (matcher.find()) {
            double cost = Double.parseDouble(matcher.group(1));
            String method = matcher.group(2).trim();
            dataset.addValue(cost, "Cost", method);
        }

        return dataset;
    }
}