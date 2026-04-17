package top.andyron.shushequ.service.article.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.andyron.shushequ.api.model.vo.article.dto.TagDTO;
import top.andyron.shushequ.service.article.repository.entity.ArticleTagDO;

import java.util.List;

/**
 * 文章标签映mapper接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTagDO> {

    /**
     * 查询文章标签
     *
     * @param articleId
     * @return
     */
    List<TagDTO> listArticleTagDetails(@Param("articleId") Long articleId);



}
