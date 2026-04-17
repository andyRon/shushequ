package top.andyron.shushequ.api.model.vo.article.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 创作历程
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@ToString(callSuper = true)
public class YearArticleDTO {

    /**
     * 年份
     */
    private String year;

    /**
     * 文章数量
     */
    private Integer articleCount;
}
