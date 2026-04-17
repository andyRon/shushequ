package top.andyron.shushequ.api.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Schema(description = "教程排序")
public class SortColumnArticleReq implements Serializable {
    // 排序前的文章 ID
    @Schema(description = "排序前的文章 ID")
    private Long activeId;

    // 排序后的文章 ID
    @Schema(description = "排序后的文章 ID")
    private Long overId;

}
