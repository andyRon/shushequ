package top.andyron.shushequ.api.model.vo.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import top.andyron.shushequ.api.model.cdn.CdnImgSerializer;
import top.andyron.shushequ.api.model.cdn.CdnUtil;

import java.io.Serializable;

/**
 * 基本用户信息
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Accessors(chain = true)
public class SimpleUserInfoDTO implements Serializable {
    private static final long serialVersionUID = 4802653694786272120L;

    @Schema(description = "作者ID")
    private Long userId;

    @Schema(description = "作者名")
    private String name;

    @Schema(description = "作者头像")
    @JsonSerialize(using = CdnImgSerializer.class)
    private String avatar;

    @Schema(description = "作者简介")
    private String profile;

    public SimpleUserInfoDTO setAvatar(String avatar) {
        this.avatar = CdnUtil.autoTransCdn(avatar);
        return this;
    }
}
