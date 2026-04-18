package top.andyron.shushequ.service.chatai.service.impl.doubao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.enums.ai.AISourceEnum;
import top.andyron.shushequ.api.model.enums.ai.AiChatStatEnum;
import top.andyron.shushequ.api.model.vo.chat.ChatItemVo;
import top.andyron.shushequ.api.model.vo.chat.ChatRecordsVo;
import top.andyron.shushequ.service.chatai.service.AbsChatService;

import java.util.function.BiConsumer;

@Slf4j
@Service
public class DoubaoAiServiceImpl extends AbsChatService {
    @Autowired
    private DoubaoIntegration doubaoIntegration;


    @Override
    public AiChatStatEnum doAnswer(Long user, ChatItemVo chat) {
        return doubaoIntegration.directAnswer(user, chat);
    }

    @Override
    public AiChatStatEnum doAnswer(Long user, ChatRecordsVo response) {
        return doubaoIntegration.directAnswer(user, response.getRecords(), response.getRecords().get(0));
    }

    @Override
    public AiChatStatEnum doAsyncAnswer(Long user, ChatRecordsVo chatRes, BiConsumer<AiChatStatEnum, ChatRecordsVo> consumer) {
        return doubaoIntegration.streamAsyncAnswer(user, chatRes, consumer);
    }

    @Override
    public AISourceEnum source() {
        return AISourceEnum.DOU_BAO_AI;
    }

    @Override
    public boolean asyncFirst() {
        return true;
    }


}
