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
@Schema(description = "拖拽移动教程顺序")
public class MoveColumnArticleOrGroupReq implements Serializable {
    // 要排序的 id
    @Schema(description = "专栏ID")
    private Long columnId;

    @Schema(description = "移动的分组")
    private Long moveGroupId;

    @Schema(description = "移动的教程")
    private Long moveArticleId;

    /**
     * 当这个不存在时，groupId必须存在，表示移动当目标分组的首个位置
     */
    @Schema(description = "目标教程")
    private Long targetArticleId;

    /**
     * 目标分组
     */
    @Schema(description = "教程分组")
    private Long targetGroupId;

    /**
     * 1 表示在目标的后面一个
     * 0 表示移动到目标里面
     * -1 表示移动到目标前面一个
     */
    @Schema(description = "移动位置")
    private Integer movePosition;
}
