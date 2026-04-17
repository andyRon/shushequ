package top.andyron.shushequ.api.model.vo.comment;

import lombok.Data;
import top.andyron.shushequ.api.model.vo.comment.dto.HighlightDto;

/**
 * 评论列表入参
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class CommentSaveReq {

    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 父评论ID
     */
    private Long parentCommentId;

    /**
     * 顶级评论ID
     */
    private Long topCommentId;

    /**
     * 引用的正文内容
     */
    private HighlightDto highlight;
}
