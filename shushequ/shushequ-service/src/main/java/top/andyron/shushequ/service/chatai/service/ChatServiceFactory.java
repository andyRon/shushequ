package top.andyron.shushequ.service.chatai.service;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Component
public class ChatServiceFactory {
    private final Map<AISourceEnum, ChatService> chatServiceMap;


    public ChatServiceFactory(List<ChatService> chatServiceList) {
        chatServiceMap = Maps.newHashMapWithExpectedSize(chatServiceList.size());
        for (ChatService chatService : chatServiceList) {
            chatServiceMap.put(chatService.source(), chatService);
        }
    }

    public ChatService getChatService(AISourceEnum aiSource) {
        return chatServiceMap.get(aiSource);
    }
}
