package top.andyron.shushequ.api.model.vo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户收款码
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayCodeDTO implements Serializable {
    private static final long serialVersionUID = -2601714252107169062L;

    /**
     * base64格式的收款二维码图片
     */
    private String qrCode;

    /**
     * 内容
     */
    private String qrMsg;
}
