package top.andyron.shushequ.service.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信菜单配置
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Component
@ConfigurationProperties(prefix = "paicoding.login.wx")
public class WxMenuProperties {
    private String appId;
    private String appSecret;
}
