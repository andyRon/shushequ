package top.andyron.shushequ.web.front.login.wx.vo;

import lombok.Data;

/**
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class WxLoginVo {
    /**
     * 验证码
     */
    private String code;

    /**
     * 二维码
     */
    private String qr;

    /**
     * true 表示需要重新建立连接
     */
    private boolean reconnect;

}
