package org.scg.mcpserver;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/6/30
 */
@SpringBootTest
@AutoConfigureMockMvc
class MCPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testWithRealMemoryModel() throws Exception {
        mockMvc.perform(post("/api/mcp/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"chat\",\"content\":\"Hello\"}"))
                .andExpect(jsonPath("$.content").isNotEmpty());
    }
}
