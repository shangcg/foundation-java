package org.scg.freechart.utils;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/5/22
 */
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    public static Map<String, String> parseLog(String log) {
        // 正则表达式匹配执行耗时和执行类及方法名
//        String regex = "\\[([\\d.]+ms)\\]\\s*(com\\.\\S+):(\\w+\\(\\))";
        String regex = "\\[([\\d.]+ms)\\]\\s*(com\\.\\S+):(\\w+\\(\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log);

        Map<String, String> resultMap = new HashMap<>();

        while (matcher.find()) {
            String duration = matcher.group(1); // 执行耗时
            String className = matcher.group(2); // 执行类
            String methodName = matcher.group(3); // 方法名

            // 将信息封装为一个字符串，方便后续处理
            String key = className + ":" + methodName;
            String value = duration;

            resultMap.put(key, value);
        }

        return resultMap;
    }

    public static void main(String[] args) {
        String log = "";

        Map<String, String> result = parseLog(log);

        // 打印结果
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
