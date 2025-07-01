package org.scg.mcpserver.service;

import org.scg.mcpserver.model.MCPMessage;
import org.scg.mcpserver.model.MCPResponse;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/6/30
 */
public interface MCPService {
    MCPResponse processMessage(MCPMessage message);
}
