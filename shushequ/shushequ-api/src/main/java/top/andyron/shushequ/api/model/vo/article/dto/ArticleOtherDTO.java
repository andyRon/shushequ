package top.andyron.shushequ.api.model.vo.article.dto;

import lombok.Data;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class ArticleOtherDTO {
    // 文章的阅读类型
    private Integer readType;
    // 教程的翻页
    private ColumnArticleFlipDTO flip;
}
