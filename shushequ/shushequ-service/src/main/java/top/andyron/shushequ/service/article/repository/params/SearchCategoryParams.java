package top.andyron.shushequ.service.article.repository.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.vo.PageParam;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchCategoryParams extends PageParam {
    // 类目名称
    private String category;
}
