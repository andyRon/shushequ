package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

/**
 * 微信菜单校验请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuValidateReq {
    /**
     * 菜单 JSON；为空时校验当前草稿
     */
    private String menuJson;

    private WxMenuReplyDTO subscribeReply;

    private WxMenuReplyDTO defaultReply;

    private java.util.List<WxMenuKeywordReplyDTO> keywordReplies;

    private String messageFallbackStrategy;

    private String aiPrompt;
    private String aiProvider;
    private Boolean aiEnable;

    private java.util.List<WxMenuClickReplyDTO> clickReplies;
}
