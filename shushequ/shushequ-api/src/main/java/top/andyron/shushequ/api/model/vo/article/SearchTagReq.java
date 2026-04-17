package top.andyron.shushequ.api.model.vo.article;

import lombok.Data;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SearchTagReq {
    // 标签名称
    private String tag;
    // 状态
    private Integer status;
    // 分页
    private Long pageNumber;
    private Long pageSize;
}
