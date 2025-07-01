package org.scg.freechart.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArthasLogAnalyzer {

    public static void main(String[] args) {

        DefaultCategoryDataset dataset = parseLogData(logData);
        DefaultCategoryDataset top10Dataset = getTop10Methods(dataset);

        // 打印前 10 个方法及其耗时
        System.out.println("Top 10 Most Time-Consuming Methods:");
        for (Object columnKey : top10Dataset.getColumnKeys()) {
            String method = (String) columnKey;
            double cost = top10Dataset.getValue("Cost", method).doubleValue();
            System.out.printf("%s: %.6f ms%n", method, cost);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Top 10 Most Time-Consuming Methods",
                "Methods",
                "Cost (ms)",
                top10Dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1200, 800));

        JFrame frame = new JFrame("Top 10 Methods Bar Chart");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static DefaultCategoryDataset parseLogData(String logData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // 优化后的正则表达式
        Pattern pattern = Pattern.compile("\\[(?:\\d+\\.?\\d*%\\s+)?([\\d.]+)ms\\]\\s*([\\w.]+):(\\w+)\\(\\)");
        Matcher matcher = pattern.matcher(logData);

        while (matcher.find()) {
            try {
                double cost = Double.parseDouble(matcher.group(1));
                // 拼接方法名，格式为 类名.方法名()
                String method = matcher.group(2) + "." + matcher.group(3) + "()";
                dataset.addValue(cost, "Cost", method);
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse time for line: " + matcher.group());
            }
        }
        return dataset;
    }

    private static DefaultCategoryDataset getTop10Methods(DefaultCategoryDataset dataset) {
        List<Map.Entry<String, Double>> methodCostList = new ArrayList<>();
        for (Object columnKey : dataset.getColumnKeys()) {
            String method = (String) columnKey;
            double cost = dataset.getValue("Cost", method).doubleValue();
            methodCostList.add(new AbstractMap.SimpleEntry<>(method, cost));
        }

        // 按耗时降序排序
        methodCostList.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

        DefaultCategoryDataset top10Dataset = new DefaultCategoryDataset();
        int count = 0;
        for (Map.Entry<String, Double> entry : methodCostList) {
            if (count >= 10) {
                break;
            }
            top10Dataset.addValue(entry.getValue(), "Cost", entry.getKey());
            count++;
        }
        return top10Dataset;
    }

    // 后续可替换为实际的 logData
    static String logData = "";
}