package top.andyron.shushequ.service.user.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.entity.BaseDO;

/**
 * 用户登录表
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方用户ID
     */
    private String thirdAccountId;

    /**
     * 登录方式: 0-微信登录，1-账号密码登录
     */
    private Integer loginType;

    /**
     * 删除标记
     */
    private Integer deleted;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 登录密码，密文存储
     */
    private String password;

}
