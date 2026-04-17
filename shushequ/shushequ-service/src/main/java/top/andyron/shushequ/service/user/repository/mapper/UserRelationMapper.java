package top.andyron.shushequ.service.user.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
api.model.vo.PageParam;
api.model.vo.user.dto.FollowUserInfoDTO;
import org.apache.ibatis.annotations.Param;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.user.dto.FollowUserInfoDTO;
import top.andyron.shushequ.service.user.repository.entity.UserRelationDO;

import java.util.List;

/**
 * 用户关系mapper接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface UserRelationMapper extends BaseMapper<UserRelationDO> {

    /**
     * 我关注的用户
     * @param followUserId
     * @param pageParam
     * @return
     */
    List<FollowUserInfoDTO> queryUserFollowList(@Param("followUserId") Long followUserId, @Param("pageParam") PageParam pageParam);

    /**
     * 关注我的粉丝
     * @param userId
     * @param pageParam
     * @return
     */
    List<FollowUserInfoDTO> queryUserFansList(@Param("userId") Long userId, @Param("pageParam") PageParam pageParam);
}
