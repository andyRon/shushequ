package top.andyron.shushequ.service.article.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleAdminDTO;
import top.andyron.shushequ.api.model.vo.article.dto.SimpleArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.YearArticleDTO;
import top.andyron.shushequ.service.article.repository.entity.ArticleDO;
import top.andyron.shushequ.service.article.repository.entity.ReadCountDO;
import top.andyron.shushequ.service.article.repository.params.SearchArticleParams;

import java.util.List;

/**
 * 文章mapper接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ArticleMapper extends BaseMapper<ArticleDO> {

    /**
     * 通过id遍历文章, 用于生成sitemap.xml
     *
     * @param lastId
     * @param size
     * @return
     */
    List<SimpleArticleDTO> listArticlesOrderById(@Param("lastId") Long lastId, @Param("size") int size);

    /**
     * 根据阅读次数获取热门文章
     *
     * @param pageParam
     * @return
     */
    List<SimpleArticleDTO> listArticlesByReadCounts(@Param("pageParam") PageParam pageParam);

    /**
     * 查询作者的热门文章
     *
     * @param userId
     * @param pageParam
     * @return
     */
    List<SimpleArticleDTO> listArticlesByUserIdOrderByReadCounts(@Param("userId") Long userId, @Param("pageParam") PageParam pageParam);

    /**
     * 根据类目 + 标签查询文章
     *
     * @param category
     * @param tagIds
     * @param pageParam
     * @return
     */
    List<ReadCountDO> listArticleByCategoryAndTags(@Param("categoryId") Long category,
                                                   @Param("tags") List<Long> tagIds,
                                                   @Param("pageParam") PageParam pageParam);

    /**
     * 根据用户ID获取创作历程
     *
     * @param userId
     * @return
     */
    List<YearArticleDTO> listYearArticleByUserId(@Param("userId") Long userId);

    List<ArticleAdminDTO> listArticlesByParams(@Param("searchParams") SearchArticleParams searchArticleParams,
                                               @Param("pageParam") PageParam pageParam);

    Long countArticlesByParams(@Param("searchParams") SearchArticleParams searchArticleParams);
}
