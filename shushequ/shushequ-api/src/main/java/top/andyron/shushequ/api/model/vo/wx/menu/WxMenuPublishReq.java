package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

/**
 * 微信菜单发布请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuPublishReq {
    /**
     * 菜单 JSON；为空时发布当前草稿
     */
    private String menuJson;
}
