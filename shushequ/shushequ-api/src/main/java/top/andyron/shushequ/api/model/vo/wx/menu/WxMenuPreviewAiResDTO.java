package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信菜单 AI 预览结果
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuPreviewAiResDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success;
    private String replyText;
    private String provider;
    private String errorMsg;
}
