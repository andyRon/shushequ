package top.andyron.shushequ.api.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author andyron
 * @date 2026/4/17
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    /**
     * 微信登录
     */
    WECHAT(0),
    /**
     * 用户名+密码登录
     */
    USER_PWD(1),
    /**
     * 知识星球登录
     */
    ZSXQ(2),
    ;
    private int type;
}