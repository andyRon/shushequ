package top.andyron.shushequ.service.user.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.entity.BaseDO;

/**
 * 用户关系表
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_relation")
public class UserRelationDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主用户ID
     */
    private Long userId;

    /**
     * 粉丝用户ID
     */
    private Long followUserId;

    /**
     * 关注状态: 0-未关注，1-已关注，2-取消关注
     */
    private Integer followState;
}
