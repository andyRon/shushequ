package top.andyron.shushequ.api.model.vo.config.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 敏感词命中统计
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SensitiveWordHitDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 命中的词
     */
    private String word;

    /**
     * 命中次数
     */
    private Integer hitCount;
}
