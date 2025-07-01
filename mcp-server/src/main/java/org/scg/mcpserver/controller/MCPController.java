package org.scg.mcpserver.controller;

import org.scg.mcpserver.model.MCPMessage;
import org.scg.mcpserver.model.MCPResponse;
import org.scg.mcpserver.service.MCPService;
import org.scg.mcpserver.service.WeatherService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/6/30
 */
@RestController
@RequestMapping("/mcp")
public class MCPController {

    private final MCPService mcpService;

    private final WeatherService weatherService;

    public MCPController(MCPService mcpService, WeatherService weatherService) {
        this.mcpService = mcpService;
        this.weatherService = weatherService;
    }

    @GetMapping("/messages/sse")
    public String handleMessage() {
//        MCPMessage message = new MCPMessage();
//        return mcpService.processMessage(message);
        return weatherService.getWeather("北京");
    }
}