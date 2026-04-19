package top.andyron.shushequ.web.front.article.vo;

import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.CategoryDTO;
import top.andyron.shushequ.api.model.vo.article.dto.TagDTO;
import lombok.Data;

import java.util.List;

/**
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class ArticleEditVo {

    private ArticleDTO article;

    private List<CategoryDTO> categories;

    private List<TagDTO> tags;

}
