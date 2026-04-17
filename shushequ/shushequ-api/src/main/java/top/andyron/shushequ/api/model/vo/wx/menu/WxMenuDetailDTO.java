package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信菜单详情
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private WxMenuConfigDTO draftConfig;
    private String draftJson;
    private String draftComment;
    private WxMenuTreeDTO draftMenu;
    private Boolean draftValid;
    private List<String> draftErrors;
    private List<String> draftWarnings;
    private WxMenuReplyDTO subscribeReply;
    private WxMenuReplyDTO defaultReply;
    private List<WxMenuKeywordReplyDTO> keywordReplies;
    private String messageFallbackStrategy;
    private String aiPrompt;
    private String aiProvider;
    private Boolean aiEnable;
    private List<WxMenuAiProviderDTO> aiProviderOptions;
    private List<WxMenuClickReplyDTO> clickReplies;

    private String remoteJson;
    private WxMenuTreeDTO remoteMenu;
    private Integer conditionalMenuCount;
    private String remoteError;

    private String menuJsonTemplate;
    private List<String> menuJsonTips;
    private List<String> replyTips;
}
