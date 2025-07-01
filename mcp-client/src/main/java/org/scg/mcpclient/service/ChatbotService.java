package org.scg.mcpclient.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {
    private final ChatClient chatClient;

    ChatbotService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // 向服务端发送请求 WeatherService.getWeather()
    // 在客户端的配置文件中，指定服务端的地址和连接信息
    public String chat(String question) {
        return chatClient.prompt().user(question).call().content();
    }
}
