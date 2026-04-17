package top.andyron.shushequ.api.model.vo.article.dto;

import lombok.Data;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class ColumnArticleFlipDTO {
    String prevHref;
    Boolean prevShow;
    String nextHref;
    Boolean nextShow;
}
