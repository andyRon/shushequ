package top.andyron.shushequ.api.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * AI生成SEO标题和描述响应
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Schema(description = "AI生成SEO标题和描述响应")
public class AiSeoGenerateRes {
    
    @Schema(description = "长标题")
    private String title;
    
    @Schema(description = "描述")
    private String description;
}
