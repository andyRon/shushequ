package top.andyron.shushequ.api.model.vo.article;

import lombok.Data;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SearchCategoryReq {
    // 类目名称
    private String category;
    // 分页
    private Long pageNumber;
    private Long pageSize;

}
