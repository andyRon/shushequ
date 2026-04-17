package top.andyron.shushequ.api.model.vo.config;

import lombok.Data;

/**
 * 敏感词命中统计分页查询
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SearchSensitiveWordHitReq {
    /**
     * 页码，从 1 开始
     */
    private Long pageNumber;

    /**
     * 每页条数
     */
    private Long pageSize;
}
