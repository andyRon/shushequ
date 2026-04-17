package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * click 菜单回复配置
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuClickReplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * click 菜单对应的 key
     */
    private String key;

    /**
     * 备注，便于后台识别
     */
    private String title;

    /**
     * 回复内容
     */
    private WxMenuReplyDTO reply;
}
