package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信菜单可选 AI provider
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuAiProviderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String value;
    private String name;
    private Boolean syncSupport;
}
