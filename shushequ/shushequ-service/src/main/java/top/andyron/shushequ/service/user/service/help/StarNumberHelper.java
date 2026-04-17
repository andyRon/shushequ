package top.andyron.shushequ.service.user.service.help;
import org.springframework.stereotype.Component;
import top.andyron.shushequ.service.user.service.conf.AiConfig;

import javax.annotation.Resource;

/**
 * 密码加密器，后续接入SpringSecurity之后，可以使用 PasswordEncoder 进行替换
 *
 * @author andyron
 * @date 2026/4/17
 */
@Component
public class StarNumberHelper {
    @Resource
    private AiConfig aiConfig;

    public Boolean checkStarNumber(String starNumber) {
        // 判断编号是否在 0 - maxStarNumber 之间
        return Integer.parseInt(starNumber) >= 0 && Integer.parseInt(starNumber) <= aiConfig.getMaxNum().getStarNumber();
    }

}
