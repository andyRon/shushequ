package top.andyron.shushequ.api.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 生成文章语义URL请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Schema(description = "生成文章语义URL请求")
public class AiSlugGenerateReq {

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章短标题")
    private String shortTitle;
}
