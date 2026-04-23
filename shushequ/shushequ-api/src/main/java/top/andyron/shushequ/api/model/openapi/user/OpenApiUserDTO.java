package top.andyron.shushequ.api.model.openapi.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class OpenApiUserDTO implements Serializable {
    private static final long serialVersionUID = 4663622879892017339L;
    /**
     * 用户id
     */
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    /**
     * 登录用户名
     */
    @Schema(description = "登录用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String loginName;

    /**
     * 用户角色 admin, normal
     */
    @Schema(description = "角色", example = "ADMIN|NORMAL")
    private String role;

    /**
     * 用户图像
     */
    @Schema(description = "用户头像")
    private String photo;

    /**
     * 用户的邮箱
     */
    @Schema(description = "用户邮箱", example = "paicoding@126.com")
    private String email;

    /**
     * 个人简介
     */
    @Schema(description = "用户简介")
    private String profile;
    /**
     * 职位
     */
    @Schema(description = "个人职位")
    private String position;

    /**
     * 公司
     */
    @Schema(description = "公司")
    private String company;


    @Schema(description = "微信id")
    private String wxId;

    /**
     * 星球id
     */
    @Schema(description = "星球id")
    private String zsxqId;

    /**
     * 星球到期时间(秒)
     */
    @Schema(description = "星球到期时间(秒)")
    private Long zsxqExpireTime;
}
