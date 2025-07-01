package org.scg.mcpserver.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Tool(description = "根据城市名称获取天气预报信息")
    public String getWeather(@ToolParam(description = "城市") String cityName) {
        return cityName + "天气阳光明媚～";
    }

    @Tool(description = "根据城市名称获取空气质量")
    public String getAirQuality(@ToolParam(description = "城市") String cityName) {
        return cityName + "空气质量很好～";
    }
}
