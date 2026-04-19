package top.andyron.shushequ.web.front.article.vo;

import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import lombok.Data;

/**
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class ArticleListVo {
    /**
     * 归档类型
     */
    private String archives;
    /**
     * 归档id
     */
    private Long archiveId;

    private PageListVo<ArticleDTO> articles;
}
