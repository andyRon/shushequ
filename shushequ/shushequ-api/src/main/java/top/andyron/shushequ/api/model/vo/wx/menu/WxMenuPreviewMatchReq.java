package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

/**
 * 微信菜单命中预览请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuPreviewMatchReq extends WxMenuSaveReq {
    private String eventType;
    private String eventKey;
    private String content;
}
