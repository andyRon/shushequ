package top.andyron.shushequ.api.model.vo.ai.config.dto;

import lombok.Data;
import top.andyron.shushequ.api.model.enums.ai.AISourceEnum;

import java.io.Serializable;

/**
 * AI 连通性测试结果
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class AiConfigTestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private AISourceEnum source;

    private Boolean success;

    private String message;

    private String answer;

    private Long costMs;
}
