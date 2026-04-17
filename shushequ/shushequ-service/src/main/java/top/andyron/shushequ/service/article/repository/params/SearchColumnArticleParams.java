package top.andyron.shushequ.service.article.repository.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.vo.PageParam;

/**
 * 专栏查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchColumnArticleParams extends PageParam {

    /**
     * 专栏名称
     */
    private String column;

    /**
     * 专栏id
     */
    private Long columnId;

    /**
     * 文章标题
     */
    private String articleTitle;
}
