package top.andyron.shushequ.service.article.repository.params;

import lombok.Data;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class ColumnArticleParams {
    // 教程 ID
    private Long columnId;
    // 文章 ID
    private Long articleId;
    // section
    private Integer section;
}
