package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信被动回复图文项
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuReplyArticleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private String picUrl;
    private String url;
}
