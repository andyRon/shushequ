package top.andyron.shushequ.api.model.vo.statistics.dto;

import lombok.Data;

/**
 * 每天的统计计数
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class StatisticsDayDTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 数量
     */
    private Long pvCount;

    /**
     * UV数量
     */
    private Long uvCount;
}
