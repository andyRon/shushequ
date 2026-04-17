package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

/**
 * 微信菜单草稿保存请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuSaveReq {
    /**
     * 菜单 JSON
     */
    private String menuJson;

    /**
     * 备注，可选
     */
    private String comment;

    /**
     * 关注后回复
     */
    private WxMenuReplyDTO subscribeReply;

    /**
     * 默认兜底回复
     */
    private WxMenuReplyDTO defaultReply;

    /**
     * 关键字/事件回复规则
     */
    private java.util.List<WxMenuKeywordReplyDTO> keywordReplies;

    /**
     * none / fixed_reply / ai_reply
     */
    private String messageFallbackStrategy;

    private String aiPrompt;
    private String aiProvider;
    private Boolean aiEnable;

    /**
     * 兼容旧结构：click 菜单回复配置
     */
    private java.util.List<WxMenuClickReplyDTO> clickReplies;
}
