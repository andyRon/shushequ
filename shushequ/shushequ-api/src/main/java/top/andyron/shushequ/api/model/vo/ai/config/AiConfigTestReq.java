package top.andyron.shushequ.api.model.vo.ai.config;

import lombok.Data;
import top.andyron.shushequ.api.model.enums.ai.AISourceEnum;

/**
 * AI 连通性测试请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class AiConfigTestReq {
    /**
     * 待测试模型
     */
    private AISourceEnum source;

    /**
     * 自定义测试提示词
     */
    private String prompt;
}
