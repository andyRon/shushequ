package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信菜单发布结果
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuPublishResDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success;
    private Integer errCode;
    private String errMsg;
    private String publishedMenuJson;
}
