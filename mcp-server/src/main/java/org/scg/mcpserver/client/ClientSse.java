package org.scg.mcpserver.client;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * <p>
 *  https://cloud.tencent.com/developer/article/2515900
 * </p>
 * @author shangchenguang
 * @date 2025/7/1
 */
public class ClientSse {

    public static void main(String[] args) {
        // 修改: 更新 baseUrl 为服务端的 SSE 端点路径
        var transport = new WebFluxSseClientTransport(WebClient.builder().baseUrl("http://localhost:8080/mcp/messages"));
        var client = McpClient.sync(transport).build();
        client.initialize();
        client.ping();
        // 列出并展示可用的工具
        McpSchema.ListToolsResult toolsList = client.listTools();
        System.out.println("可用工具 = " + toolsList);

        // 获取成都的天气
        McpSchema.CallToolResult weatherForecastResult = client.callTool(new McpSchema.CallToolRequest("getWeather",
                Map.of("cityName", "成都")));
        System.out.println("返回结果: " + weatherForecastResult.content());

        client.closeGracefully();
    }
}