package org.scg.mcpserver.config;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/5/19
 */
import org.scg.mcpserver.service.GzhService;
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
    public ToolCallbackProvider gzhRecommendTools(GzhService gzhService) {
        FunctionToolCallback<GzhService, String> recommendTool = FunctionToolCallback.builder("recommend articles tools", GzhService::recommendGzhInfo)
                .description("recommend articles tools")
                .inputType(String.class)
                .build();

        FunctionToolCallback<GzhService, String> bestArticleTool = FunctionToolCallback.builder("best recommend articles tools", GzhService::bestContext)
                .description("best recommend articles tools")
                .inputType(String.class)
                .build();


        return ToolCallbackProvider.from(List.of(
                recommendTool,
                bestArticleTool
        ));
    }


    @Bean
    public MethodToolCallbackProvider myTools(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(weatherService)
                .build();
    }
}