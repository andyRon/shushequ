package top.andyron.shushequ.api.model.enums.login;

import lombok.Getter;

/**
 * 微信公众号登录二维码类型
 *
 * @author andyron
 * @date 2026/4/17
 */
@Getter
public enum LoginQrTypeEnum {

    SUBSCRIPTION_ACCOUNT("Subscription Account", "微信公众号"),
    SERVICE_ACCOUNT("Service Account", "服务号"),
    ;
    private String code;
    private String desc;

    LoginQrTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
