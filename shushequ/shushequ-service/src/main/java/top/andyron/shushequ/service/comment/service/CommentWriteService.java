package top.andyron.shushequ.service.comment.service;

/**
 * 评论Service接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface CommentWriteService {

    /**
     * 更新/保存评论
     *
     * @param commentSaveReq
     * @return
     */
    Long saveComment(CommentSaveReq commentSaveReq);

    /**
     * 删除评论
     *
     * @param commentId
     * @throws Exception
     */
    void deleteComment(Long commentId, Long userId);

}
