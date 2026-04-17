package top.andyron.shushequ.api.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AI生成SEO标题和描述请求
 *
 * @author andyron
 * @date 2026/4/17
 */

@Data
@Schema(description = "AI生成SEO标题和描述请求")
public class AiSeoGenerateReq {

    @Schema(description = "短标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String shortTitle;

    @Schema(description = "正文前400字", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
}
