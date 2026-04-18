package top.andyron.shushequ.service.comment.service;

import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.comment.CommentSaveReq;
import top.andyron.shushequ.api.model.vo.comment.SearchCommentReq;
import top.andyron.shushequ.api.model.vo.comment.dto.CommentAdminDTO;

public interface CommentSettingService {

    PageVo<CommentAdminDTO> getCommentList(SearchCommentReq req);

    CommentAdminDTO getCommentDetail(Long commentId);

    Long saveComment(CommentSaveReq req, Long operateUserId);

    void deleteComment(Long commentId);
}
