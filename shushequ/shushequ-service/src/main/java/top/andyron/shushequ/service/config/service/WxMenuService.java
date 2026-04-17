package top.andyron.shushequ.service.config.service;

import top.andyron.shushequ.api.model.vo.wx.menu.*;

/**
 * 微信菜单管理
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface WxMenuService {
    WxMenuDetailDTO getDetail();

    void saveDraft(WxMenuSaveReq req);

    WxMenuValidateResDTO validate(WxMenuValidateReq req);

    WxMenuPublishResDTO publish(WxMenuPublishReq req);

    WxMenuDetailDTO syncRemoteToDraft();

    WxMenuReplyDTO getSubscribeReply();

    WxMenuReplyDTO getDefaultReply();

    WxMenuReplyDTO matchKeywordReply(String eventType, String eventKey, String content);

    String getMessageFallbackStrategy();

    Boolean getAiEnable();

    String getAiPrompt();

    String getAiProvider();

    WxMenuReplyDTO getClickReply(String eventKey);

    WxMenuPreviewMatchResDTO previewMatch(WxMenuPreviewMatchReq req);

    WxMenuPreviewAiResDTO previewAi(WxMenuPreviewAiReq req);
}
