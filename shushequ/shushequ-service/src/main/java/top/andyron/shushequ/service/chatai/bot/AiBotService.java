package top.andyron.shushequ.service.chatai.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import top.andyron.shushequ.api.model.enums.ai.AiBotEnum;
import top.andyron.shushequ.api.model.vo.user.dto.BaseUserInfoDTO;
import top.andyron.shushequ.core.util.SpringUtil;
import top.andyron.shushequ.service.chatai.ChatFacade;
import top.andyron.shushequ.service.user.repository.dao.UserDao;
import top.andyron.shushequ.service.user.service.RegisterService;
import top.andyron.shushequ.service.user.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 基于大模型的杠精机器人
 *
 * @author andyron
 * @date 2026/4/17
 */
@Component
public class AiBotService {

    @Autowired
    private ChatFacade chatFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserDao userDao;

    private Map<AiBotEnum, BaseUserInfoDTO> botUsers = new HashMap<>();

    /**
     * 在应用完全启动后初始化 AI 机器人用户
     * 使用 ApplicationReadyEvent 确保 Liquibase 已完成数据库表创建
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initBotUser() {
        String ossPrefix = SpringUtil.getConfig("view.site.oss", "");
        for (AiBotEnum bot : AiBotEnum.values()) {
            BaseUserInfoDTO user = userService.queryUserByLoginName(bot.getUserName());
            String avatarUrl = buildAvatarUrl(ossPrefix, bot.getAvatar());
            
            if (user == null) {
                Long userId = registerService.registerSystemUser(bot.getUserName(), bot.getNickName(), avatarUrl);
                user = userService.queryBasicUserInfo(userId);
            } else {
                UserInfoDO userInfoDO = userDao.getByUserId(user.getUserId());
                if (!avatarUrl.equals(userInfoDO.getPhoto())) {
                    userInfoDO.setPhoto(avatarUrl);
                    userDao.updateUserInfo(userInfoDO);
                    user = userService.queryBasicUserInfo(user.getUserId());
                }
            }
            botUsers.put(bot, user);
        }
    }

    private String buildAvatarUrl(String ossPrefix, String avatarPath) {
        if (avatarPath == null || avatarPath.isEmpty() || avatarPath.startsWith("http://") || avatarPath.startsWith("https://")) {
            return avatarPath;
        }
        if (ossPrefix == null || ossPrefix.isEmpty()) {
            return avatarPath;
        }

        boolean prefixEndsWithSlash = ossPrefix.endsWith("/");
        boolean pathStartsWithSlash = avatarPath.startsWith("/");
        if (prefixEndsWithSlash && pathStartsWithSlash) {
            return ossPrefix.substring(0, ossPrefix.length() - 1) + avatarPath;
        }
        if (!prefixEndsWithSlash && !pathStartsWithSlash) {
            return ossPrefix + "/" + avatarPath;
        }
        return ossPrefix + avatarPath;
    }

    /**
     * 触发AI机器人
     *
     * @param question
     * @return
     */
    public void trigger(AiBotEnum bot, String question, String sourceBizId, Consumer<String> consumer) {
        BaseUserInfoDTO user = botUsers.get(bot);
        AsyncUtil.execute(() -> {
            // 设置AI机器人问答上下文
            ReqInfoContext.ReqInfo reqInfo = new ReqInfoContext.ReqInfo();
            reqInfo.setUser(user);
            reqInfo.setUserId(user.getUserId());
            reqInfo.setChatId(sourceBizId);
            ReqInfoContext.addReqInfo(reqInfo);

            // 机器人，默认使用智谱模型
            chatFacade.autoChat(AISourceEnum.ZHI_PU_AI, question, vo -> {
                ChatItemVo item = vo.getRecords().get(0);
                if (item.getAnswerType() == ChatAnswerTypeEnum.JSON
                        || item.getAnswerType() == ChatAnswerTypeEnum.TEXT
                        || item.getAnswerType() == ChatAnswerTypeEnum.STREAM_END) {
                    try {
                        consumer.accept(item.getAnswer());
                    } finally {
                        // 清空上下文信息
                        ReqInfoContext.clear();
                    }
                }
            });
        });
    }

    /**
     * 获取杠精机器人相关信息
     *
     * @return
     */
    public AiBotEnum getBotEnumByUserId(Long userId) {
        for (Map.Entry<AiBotEnum, BaseUserInfoDTO> entry : botUsers.entrySet()) {
            if (Objects.equals(entry.getValue().getUserId(), userId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public BaseUserInfoDTO getBotEnumByUserId(AiBotEnum bot) {
        return botUsers.get(bot);
    }
}
