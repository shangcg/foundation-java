package org.scg.freechart.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class WeatherChartUtils {

    // ��持的日期格式
    private static final String[] DATE_FORMATS = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    /**
     * 解析天气数据，返回Map<星期, 天气>
     * @param lines 天气数据行
     * @return Map<星期, 天气>
     */
    public static Map<String, String> parseWeatherData(List<String> lines) {
        Map<String, String> result = new LinkedHashMap<>();
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length < 3) continue;
            String dateStr = parts[0];
            String week = parts[1];
            String weather = parts.length > 2 ? parts[2] : "";
            // 校验日期格式
            if (!isValidDate(dateStr)) continue;
            result.put(week, weather);
        }
        return result;
    }

    private static boolean isValidDate(String dateStr) {
        for (String fmt : DATE_FORMATS) {
            try {
                new SimpleDateFormat(fmt).parse(dateStr);
                return true;
            } catch (ParseException ignored) {}
        }
        return false;
    }

    /**
     * 生成天气柱状图
     * @param weekWeather Map<星期, 天气>
     * @param weatherColorMap 天气-颜色映射
     * @param chartTitle 标题
     * @param outputFile 输出文件
     * @param is3D 是否3D
     * @throws IOException
     */
    public static void createWeatherBarChart(
            Map<String, String> weekWeather,
            Map<String, Paint> weatherColorMap,
            String chartTitle,
            File outputFile,
            boolean is3D
    ) throws IOException {
        // 统计每种天气出现次数
        Map<String, Integer> weatherCount = new LinkedHashMap<>();
        for (String weather : weekWeather.values()) {
            if (weather == null || weather.isEmpty()) continue;
            weatherCount.put(weather, weatherCount.getOrDefault(weather, 0) + 1);
        }

        // 构建数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, String> entry : weekWeather.entrySet()) {
            String week = entry.getKey();
            String weather = entry.getValue();
            if (weather == null || weather.isEmpty()) continue;
            dataset.addValue(1, weather, week);
        }

        // 统一使用 createBarChart
        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle, "星期", "天气出现次数",
                dataset, PlotOrientation.VERTICAL,
                true, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new BarRenderer();
        setBarColors(renderer, dataset, weatherColorMap);

        // 3D效果通过阴影和渐变模拟
        if (is3D) {
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setShadowVisible(true);
        } else {
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setShadowVisible(false);
        }
        plot.setRenderer(renderer);

        ChartUtils.saveChartAsPNG(outputFile, chart, 800, 400);
    }

    // 设置柱状图颜色
    private static void setBarColors(BarRenderer renderer, DefaultCategoryDataset dataset, Map<String, Paint> weatherColorMap) {
        int seriesCount = dataset.getRowCount();
        for (int i = 0; i < seriesCount; i++) {
            String weather = (String) dataset.getRowKey(i);
            Paint color = weatherColorMap.getOrDefault(weather, Color.GRAY);
            renderer.setSeriesPaint(i, color);
        }
    }

    public static void main(String[] args) throws IOException {
        // 示例数据
        List<String> lines = Arrays.asList(
                "2025-05-22 星期三 晴",
                "2025-05-23 星期四 多云",
                "2025-05-24 星期五 小雨",
                "2025-05-25 星期六 大雨",
                "2025-05-26 星期日 暴雨",
                "2025-05-27 星期一 晴",
                "2025-05-28 星期二 多云",
                "2025-05-29 星期三 小雨",
                "2025-05-30 星期四"
        );

        // 解析数据
        Map<String, String> weekWeather = parseWeatherData(lines);

        // 自定义天气颜色
        Map<String, Paint> weatherColorMap = new HashMap<>();
        weatherColorMap.put("晴", Color.YELLOW);
        weatherColorMap.put("多云", Color.LIGHT_GRAY);
        weatherColorMap.put("小雨", Color.CYAN);
        weatherColorMap.put("大雨", Color.BLUE);
        weatherColorMap.put("暴雨", Color.MAGENTA);

        // 输出文件
        File outputFile = new File("weather_chart.png");

        // 生成柱状图（平面）
        createWeatherBarChart(weekWeather, weatherColorMap, "一周天气统计（平面）", outputFile, false);

        // 生成柱状图（3D效果）
        File outputFile3D = new File("weather_chart_3d.png");
        createWeatherBarChart(weekWeather, weatherColorMap, "一周天气统计（3D效果）", outputFile3D, true);

        System.out.println("柱状图已生成: " + outputFile.getAbsolutePath());
        System.out.println("3D柱状图已生成: " + outputFile3D.getAbsolutePath());
    }
}
