package org.scg.mcpserver.service;

import org.scg.mcpserver.model.MCPMessage;
import org.scg.mcpserver.model.MCPResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/6/30
 */
@Service
public class MCPServiceImpl implements MCPService {

    private final ChatClient chatClient;

    public MCPServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public MCPResponse processMessage(MCPMessage message) {
        String promptText = buildPrompt(message);
        String aiResponse = chatClient.prompt()
                .user(promptText)
                .call()
                .content();

        return new MCPResponse(
                message.getType() + "_response",
                aiResponse,
                "success"
        );
    }

    private String buildPrompt(MCPMessage message) {
        return switch (message.getType()) {
                    case "chat" -> String.format(
                            "Player %s says: %s. Respond as if you're in the game world.",
                            message.getPlayerId(), message.getContent());
                    case "command" -> String.format(
                            "Player %s executed command: %s. Respond with the command result.",
                            message.getPlayerId(), message.getContent());
                    default -> throw new IllegalArgumentException("Unknown message type");
                }; // 确保 switch 语句正确结束
    }
}
