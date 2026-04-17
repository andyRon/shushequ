package top.andyron.shushequ.api.model.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
@Schema(description = "后台评论查询")
public class SearchCommentReq {

    @Schema(description = "评论ID")
    private Long commentId;

    @Schema(description = "文章ID")
    private Long articleId;

    @Schema(description = "文章标题")
    private String articleTitle;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "评论类型: -1/空-全部, 1-顶级评论, 2-回复")
    private Integer commentType;

    @Schema(description = "请求页数，从1开始计数")
    private long pageNumber;

    @Schema(description = "请求页大小，默认为10")
    private long pageSize;
}
