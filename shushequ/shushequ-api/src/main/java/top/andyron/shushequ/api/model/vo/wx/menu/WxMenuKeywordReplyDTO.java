package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消息关键字回复规则
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuKeywordReplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * event_key_exact / content_exact / content_contains
     */
    private String matchType;

    /**
     * 关键字列表
     */
    private List<String> keywords;

    /**
     * 支持 text/news
     */
    private String replyType;

    /**
     * 实际回复内容
     */
    private WxMenuReplyDTO reply;

    private Boolean enabled;
    private Integer priority;
    private String title;
}
