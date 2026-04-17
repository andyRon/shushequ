package top.andyron.shushequ.api.model.vo.wx.menu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信菜单校验结果
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class WxMenuValidateResDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean valid;
    private String normalizedMenuJson;
    private List<String> menuErrors;
    private List<String> replyErrors;
    private List<String> errors;
    private List<String> warnings;
}
