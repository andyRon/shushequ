package top.andyron.shushequ.api.model.vo.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * 评论树状结构
 *
 * @author andyron
 * @date 2026/4/17
 */
@ToString(callSuper = true)
@Data
public class SubCommentDTO extends BaseCommentDTO {

    /**
     * 父评论ID
     */
    private Long parentCommentId;

    /**
     * 父评论内容
     */
    private String parentContent;

    /**
     * 评论数量
     */
    private Integer commentCount;

    @Override
    public int compareTo(@NotNull BaseCommentDTO o) {
        return Long.compare(this.getCommentTime(), o.getCommentTime());
    }
}
