package org.scg.mcpserver.model;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/6/30
 */
public class MCPResponse {
    private String type;
    private String content;
    private String status;

    public MCPResponse(String type, String content, String status) {
        this.type = type;
        this.content = content;
        this.status = status;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }
}