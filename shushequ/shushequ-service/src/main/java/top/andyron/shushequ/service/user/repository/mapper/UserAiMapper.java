package top.andyron.shushequ.service.user.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.user.dto.ZsxqUserInfoDTO;
import top.andyron.shushequ.service.user.repository.entity.UserAiDO;
import top.andyron.shushequ.service.user.repository.params.SearchZsxqWhiteParams;

import java.util.List;

/**
 * ai用户登录mapper接口
 *
 * @author ygl
 * @date 2022-07-18
 */
public interface UserAiMapper extends BaseMapper<UserAiDO> {

    Long countZsxqUsersByParams(@Param("searchParams") SearchZsxqWhiteParams params);

    List<ZsxqUserInfoDTO> listZsxqUsersByParams(@Param("searchParams") SearchZsxqWhiteParams params,
                                                @Param("pageParam") PageParam newPageInstance);
}
