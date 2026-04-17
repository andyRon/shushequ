package top.andyron.shushequ.service.chatai.service.impl.pai;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.enums.ai.AiChatStatEnum;
import top.andyron.shushequ.api.model.vo.chat.ChatItemVo;
import top.andyron.shushequ.service.chatai.service.AbsChatService;

import java.util.function.BiConsumer;

/**
 * 技术派价值一个亿的AI
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class PaiAiDemoServiceImpl extends AbsChatService {

    @Override
    public AISourceEnum source() {
        return AISourceEnum.PAI_AI;
    }

    @Override
    public AiChatStatEnum doAnswer(Long user, ChatItemVo chat) {
        chat.initAnswer(qa(chat.getQuestion()));
        return AiChatStatEnum.END;
    }

    @Override
    public AiChatStatEnum doAnswer(Long user, ChatRecordsVo response) {
        ChatItemVo item = response.getRecords().get(0);
        StringBuilder question = new StringBuilder();
        for (int i = response.getRecords().size() - 1; i >= 0; i--) {
            ChatItemVo record = response.getRecords().get(i);
            if (record == null || StringUtils.isBlank(record.getQuestion())) {
                continue;
            }
            if (record.getQuestion().startsWith(ChatConstants.PROMPT_TAG)) {
                question.append(record.getQuestion().substring(ChatConstants.PROMPT_TAG.length())).append("\n");
            } else {
                question.append(record.getQuestion());
            }
        }
        item.initAnswer(qa(question.toString()));
        return AiChatStatEnum.END;
    }

    @Override
    public AiChatStatEnum doAsyncAnswer(Long user, ChatRecordsVo response, BiConsumer<AiChatStatEnum, ChatRecordsVo> consumer) {
        AsyncUtil.execute(() -> {
            AsyncUtil.sleep(1500);
            ChatItemVo item = response.getRecords().get(0);
            item.appendAnswer(qa(item.getQuestion()));
            consumer.accept(AiChatStatEnum.FIRST, response);

            AsyncUtil.sleep(1200);
            item.appendAnswer("\n" + ChatConstants.SWITCH_TO_OTHER_MODEL);
            item.setAnswerType(ChatAnswerTypeEnum.STREAM_END);
            consumer.accept(AiChatStatEnum.END, response);
        });
        return AiChatStatEnum.END;
    }

    private String qa(String q) {
        String ans = q.replace("吗", "");
        ans = StringUtils.replace(ans, "？", "!");
        ans = StringUtils.replace(ans, "?", "!");
        return ans;
    }

    @Override
    protected int getMaxQaCnt(Long user) {
        return 65535;
    }
}
