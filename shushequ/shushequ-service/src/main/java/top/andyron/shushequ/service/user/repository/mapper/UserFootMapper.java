package top.andyron.shushequ.service.user.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.user.dto.ArticleFootCountDTO;
import top.andyron.shushequ.api.model.vo.user.dto.SimpleUserInfoDTO;
import top.andyron.shushequ.api.model.vo.user.dto.UserFootStatisticDTO;
import top.andyron.shushequ.service.user.repository.entity.UserFootDO;

import java.util.List;

/**
 * 用户足迹mapper接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface UserFootMapper extends BaseMapper<UserFootDO> {

    /**
     * 查询文章计数信息
     *
     * @param articleId
     * @return
     */
    ArticleFootCountDTO countArticleByArticleId(@Param("articleId") Long articleId);

    /**
     * 查询作者的文章统计
     *
     * @param author
     * @return
     */
    ArticleFootCountDTO countArticleByUserId(@Param("userId") Long author);

    /**
     * 查询作者的所有文章阅读计数
     *
     * @param author
     * @return
     */
    Integer countArticleReadsByUserId(@Param("userId") Long author);

    /**
     * 查询用户收藏的文章列表
     *
     * @param userId
     * @param pageParam
     * @return
     */
    List<Long> listCollectedArticlesByUserId(@Param("userId") Long userId, @Param("pageParam") PageParam pageParam);


    /**
     * 查询用户阅读的文章列表
     *
     * @param userId
     * @param pageParam
     * @return
     */
    List<Long> listReadArticleByUserId(@Param("userId") Long userId, @Param("pageParam") PageParam pageParam);

    /**
     * 查询文章的点赞列表
     *
     * @param documentId
     * @param type
     * @param size
     * @return
     */
    List<SimpleUserInfoDTO> listSimpleUserInfosByArticleId(@Param("documentId") Long documentId,
                                                           @Param("type") Integer type,
                                                           @Param("size") int size);


    UserFootStatisticDTO getFootCount();
}
