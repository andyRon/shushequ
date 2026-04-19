package top.andyron.shushequ.web.front.comment.vo;

import lombok.Data;

/**
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class HighlightCommentVo {
    /**
     * 划线评论id
     */
    private Long commentId;

    /**
     * 划线评论html
     */
    private String html;
}
