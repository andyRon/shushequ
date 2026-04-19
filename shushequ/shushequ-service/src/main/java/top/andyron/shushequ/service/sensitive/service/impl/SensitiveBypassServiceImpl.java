package top.andyron.shushequ.service.sensitive.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.vo.user.dto.BaseUserInfoDTO;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.service.chatai.bot.AiBots;
import top.andyron.shushequ.service.sensitive.service.SensitiveBypassService;
import top.andyron.shushequ.service.user.service.AuthorWhiteListService;
import top.andyron.shushequ.service.user.service.UserService;

/**
 * 敏感词处理豁免服务实现
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class SensitiveBypassServiceImpl implements SensitiveBypassService {

    @Autowired
    private AuthorWhiteListService authorWhiteListService;

    @Autowired
    private AiBots aiBots;

    @Autowired
    private UserService userService;

    @Override
    public boolean shouldBypassByUserId(Long userId) {
        if (userId == null) {
            return false;
        }
        if (aiBots.aiBots(userId)) {
            return true;
        }
        if (authorWhiteListService.authorInArticleWhiteList(userId)) {
            return true;
        }
        BaseUserInfoDTO user = userService.queryBasicUserInfo(userId);
        return user != null && StringUtils.equalsIgnoreCase(user.getRole(), UserRole.ADMIN.name());
    }
}
