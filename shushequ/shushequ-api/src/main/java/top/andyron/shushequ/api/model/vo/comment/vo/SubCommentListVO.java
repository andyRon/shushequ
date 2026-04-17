package top.andyron.shushequ.api.model.vo.comment.vo;

import lombok.Data;
import top.andyron.shushequ.api.model.vo.comment.dto.SubCommentDTO;

import java.util.List;

/**
 * 子评论列表响应
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SubCommentListVO {

    /**
     * 子评论列表
     */
    private List<SubCommentDTO> list;

    /**
     * 子评论总数
     */
    private Integer total;

    /**
     * 是否有更多
     */
    private Boolean hasMore;
}
