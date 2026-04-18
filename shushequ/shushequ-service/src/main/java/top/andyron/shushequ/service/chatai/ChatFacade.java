package top.andyron.shushequ.service.chatai;


import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.ai.AISourceEnum;
import top.andyron.shushequ.api.model.vo.chat.ChatRecordsVo;
import top.andyron.shushequ.core.util.SpringUtil;
import top.andyron.shushequ.service.chatai.service.ChatServiceFactory;
import top.andyron.shushequ.service.chatai.service.impl.chatgpt.ChatGptIntegration;
import top.andyron.shushequ.service.chatai.service.impl.xunfei.XunFeiIntegration;
import top.andyron.shushequ.service.chatai.service.impl.zhipu.ZhipuCodingIntegration;
import top.andyron.shushequ.service.chatai.service.impl.zhipu.ZhipuIntegration;
import top.andyron.shushequ.service.user.service.conf.AiConfig;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 聊天的门面类
 *
 * @author andyron
 * @date 2026/4/17
 */
@Slf4j
@Service
public class ChatFacade {

    @Autowired
    private AiConfig aiConfig;
    @Autowired
    private ChatServiceFactory chatServiceFactory;

    /**
     * 基于Guava的单实例缓存
     */
    private Supplier<AISourceEnum> aiSourceCache;

    /**
     * 返回推荐的AI模型
     *
     * @return
     */
    public AISourceEnum getRecommendAiSource() {
        if (aiSourceCache == null) {
            refreshAiSourceCache(Collections.emptySet());
        }
        AISourceEnum sourceEnum = aiSourceCache.get();
        if (sourceEnum == null) {
            refreshAiSourceCache(getRecommendAiSource(Collections.emptySet()));
        }
        return aiSourceCache.get();
    }

    public void refreshAiSourceCache(AISourceEnum ai) {
        aiSourceCache = Suppliers.memoizeWithExpiration(() -> ai, 10, TimeUnit.MINUTES);
    }

    public void refreshAiSourceCache(Set<AISourceEnum> except) {
        refreshAiSourceCache(getRecommendAiSource(except));
    }

    /**
     * 返回推荐的AI模型
     *
     * @param except 不选择的AI模型
     * @return
     */
    private AISourceEnum getRecommendAiSource(Set<AISourceEnum> except) {
        AISourceEnum source;
        try {
            ChatGptIntegration.ChatGptConfig config = SpringUtil.getBean(ChatGptIntegration.ChatGptConfig.class);
            if (!except.contains(AISourceEnum.CHAT_GPT_3_5) && !CollectionUtils.isEmpty(config.getConf()
                    .get(config.getMain()).getKeys())) {
                source = AISourceEnum.CHAT_GPT_3_5;
            } else if (!except.contains(AISourceEnum.ZHI_PU_AI)  && StringUtils.isNotBlank(SpringUtil.getBean(ZhipuIntegration.ZhipuConfig.class)
                    .getApiSecretKey())) {
                source = AISourceEnum.ZHI_PU_AI;
            } else if (!except.contains(AISourceEnum.ZHIPU_CODING) && StringUtils.isNotBlank(SpringUtil.getBean(ZhipuCodingIntegration.ZhipuCodingConfig.class)
                    .getApiKey())) {
                source = AISourceEnum.ZHIPU_CODING;
            } else if (!except.contains(AISourceEnum.XUN_FEI_AI) && StringUtils.isNotBlank(SpringUtil.getBean(XunFeiIntegration.XunFeiConfig.class)
                    .getApiKey())) {
                source = AISourceEnum.XUN_FEI_AI;
            } else if (!except.contains(AISourceEnum.ALI_AI)) {
                source = AISourceEnum.ALI_AI;
            } else if(!except.contains(AISourceEnum.DEEP_SEEK)) {
                source = AISourceEnum.DEEP_SEEK;
            } else if(!except.contains(AISourceEnum.DOU_BAO_AI)) {
                source = AISourceEnum.DOU_BAO_AI;
            } else {
                source = AISourceEnum.PAI_AI;
            }
        } catch (Exception e) {
            source = AISourceEnum.PAI_AI;
        }

        if (source != AISourceEnum.PAI_AI && !aiConfig.getSource().contains(source)) {
            Set<AISourceEnum> totalExcepts = Sets.newHashSet(except);
            totalExcepts.add(source);
            return getRecommendAiSource(totalExcepts);
        }
        log.info("当前选中的AI模型：{}", source);
        return source;
    }

    /**
     * 高度封装的AI聊天访问入口，对于使用这而言，只需要提问，定义接收返回结果的回调即可
     *
     * @param question 提出的问题
     * @param callback 定义异步聊天接口返回时的回调策略
     * @return 表示同步直接返回的结果
     */
    public ChatRecordsVo autoChat(String question, Consumer<ChatRecordsVo> callback) {
        AISourceEnum source = getRecommendAiSource();
        return autoChat(source, question, callback);
    }


    /**
     * 自动根据AI的支持方式，选择同步/异步的交互方式
     *
     * @param source
     * @param question
     * @param callback
     * @return
     */
    public ChatRecordsVo autoChat(AISourceEnum source, String question, Consumer<ChatRecordsVo> callback) {
        if (source.asyncSupport() && chatServiceFactory.getChatService(source).asyncFirst()) {
            // 支持异步且异步优先的场景下，自动选择异步方式进行聊天
            return asyncChat(source, question, callback);
        }
        return chat(source, question, callback);
    }

    /**
     * 开始聊天
     *
     * @param question
     * @param source
     * @return
     */
    public ChatRecordsVo chat(AISourceEnum source, String question) {
        return chatServiceFactory.getChatService(source).chat(ReqInfoContext.getReqInfo().getUserId(), question);
    }

    /**
     * 开始聊天
     *
     * @param question
     * @param source
     * @return
     */
    public ChatRecordsVo chat(AISourceEnum source, String question, Consumer<ChatRecordsVo> callback) {
        return chatServiceFactory.getChatService(source)
                .chat(ReqInfoContext.getReqInfo().getUserId(), question, callback);
    }

    /**
     * 异步聊天的方式
     *
     * @param source
     * @param question
     */
    public ChatRecordsVo asyncChat(AISourceEnum source, String question, Consumer<ChatRecordsVo> callback) {
        return chatServiceFactory.getChatService(source)
                .asyncChat(ReqInfoContext.getReqInfo().getUserId(), question, callback);
    }

    /**
     * 返回历史聊天记录
     *
     * @param source
     * @return
     */
    public ChatRecordsVo history(AISourceEnum source) {
        source = source == null ? getRecommendAiSource() : source;
        return chatServiceFactory.getChatService(source).getChatHistory(ReqInfoContext.getReqInfo().getUserId(), source);
    }
}
