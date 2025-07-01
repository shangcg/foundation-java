package org.scg.mcpclient.controller;


import org.scg.mcpclient.service.ChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final ChatbotService chatbotService;

    public MCPController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/message")
    public String handleMessage() {
        return chatbotService.chat("今天北京的天气如何？");
    }
}