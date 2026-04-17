package top.andyron.shushequ.api.model.vo.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论树状结构
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class TopCommentDTO extends BaseCommentDTO {
    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 子评论
     */
    private List<SubCommentDTO> childComments;

    /**
     * 子评论总数（用于显示"展开N条回复"）
     */
    private Integer childCommentCount;

    /**
     * 是否有更多子评论（用于前端判断是否显示展开按钮）
     */
    private Boolean hasMoreChild;

    public List<SubCommentDTO> getChildComments() {
        if (childComments == null) {
            childComments = new ArrayList<>();
        }
        return childComments;
    }

    @Override
    public int compareTo(@NotNull BaseCommentDTO o) {
        return Long.compare(o.getCommentTime(), this.getCommentTime());
    }
}
