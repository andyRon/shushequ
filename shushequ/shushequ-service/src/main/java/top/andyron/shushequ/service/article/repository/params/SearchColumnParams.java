package top.andyron.shushequ.service.article.repository.params;

import lombok.Data;
import top.andyron.shushequ.api.model.vo.PageParam;

/**
 * 专栏查询
 */
@Data
public class SearchColumnParams extends PageParam {

    /**
     * 专栏名称
     */
    private String column;
}
