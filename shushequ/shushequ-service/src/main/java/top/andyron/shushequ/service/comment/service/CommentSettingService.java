package top.andyron.shushequ.service.comment.service;


public interface CommentSettingService {

    PageVo<CommentAdminDTO> getCommentList(SearchCommentReq req);

    CommentAdminDTO getCommentDetail(Long commentId);

    Long saveComment(CommentSaveReq req, Long operateUserId);

    void deleteComment(Long commentId);
}
