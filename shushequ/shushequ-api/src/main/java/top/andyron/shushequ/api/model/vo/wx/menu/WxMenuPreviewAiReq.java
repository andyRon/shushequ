package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

/**
 * 微信菜单 AI 预览请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuPreviewAiReq {
    private String content;
    private String aiPrompt;
    private String aiProvider;
    private Boolean aiEnable;
}
