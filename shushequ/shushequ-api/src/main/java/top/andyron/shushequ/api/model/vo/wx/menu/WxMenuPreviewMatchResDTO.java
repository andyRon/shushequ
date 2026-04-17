package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信菜单命中预览结果
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuPreviewMatchResDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean matched;
    private String matchedRuleTitle;
    private String matchedRuleType;
    private String matchedKeyword;
    private WxMenuReplyDTO reply;
    private String fallbackStrategy;
    private Boolean usedFallback;
}
