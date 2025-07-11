package org.scg.mcpserver.config;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/5/19
 */
import org.scg.mcpserver.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ToolCallbackProviderConfig {
    @Bean
    public MethodToolCallbackProvider myTools(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(weatherService)
                .build();
    }
}