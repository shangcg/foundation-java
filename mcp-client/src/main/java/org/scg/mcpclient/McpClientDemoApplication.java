package org.scg.mcpclient;

import org.scg.mcpclient.service.ChatbotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class McpClientDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(McpClientDemoApplication.class, args);
		ChatbotService chatbotService = context.getBean(ChatbotService.class);
		String response = chatbotService.chat("今天北京的天气如何？");
		System.out.println("Response from server: " + response);
	}

}
