package top.andyron.shushequ.service.chatai.service;

import top.andyron.shushequ.api.model.enums.ai.AISourceEnum;
import top.andyron.shushequ.api.model.vo.chat.ChatItemVo;
import top.andyron.shushequ.api.model.vo.chat.ChatSessionItemVo;

import java.util.List;

/**
 * 对话会话记录服务
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ChatHistoryService {
    /**
     * 获取对话列表
     *
     * @param source AI模型
     * @return
     */
    List<ChatSessionItemVo> listChatSessions(AISourceEnum source, Long userId);

    /**
     * 获取对话记录
     *
     * @param source AI模型
     * @param chatId 对话id
     * @param size   记录条数
     * @return 对话记录
     */
    List<ChatItemVo> listHistory(AISourceEnum source, Long userId, String chatId, Integer size);

    /**
     * 保存最新的一条对话内容
     *
     * @param source AI模型
     * @param chatId 对话id
     * @param item   对话内容
     */
    void saveRecord(AISourceEnum source, Long userId, String chatId, ChatItemVo item);


    Boolean updateChatSessionName(AISourceEnum source, String chatId, String title, Long userId);

    Boolean removeChatSession(AISourceEnum source, String chatId, Long userId);
}
