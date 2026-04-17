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
public class SearchTagParams extends PageParam {
    // 标签名称
    private String tag;
    // 状态
    private Integer status;
}
