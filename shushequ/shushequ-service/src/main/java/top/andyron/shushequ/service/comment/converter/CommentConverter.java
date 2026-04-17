package top.andyron.shushequ.service.comment.converter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import top.andyron.shushequ.api.model.vo.comment.CommentSaveReq;
import top.andyron.shushequ.api.model.vo.comment.dto.BaseCommentDTO;
import top.andyron.shushequ.api.model.vo.comment.dto.HighlightDto;
import top.andyron.shushequ.api.model.vo.comment.dto.SubCommentDTO;
import top.andyron.shushequ.api.model.vo.comment.dto.TopCommentDTO;
import top.andyron.shushequ.service.comment.repository.entity.CommentDO;

import java.util.ArrayList;

/**
 * 评论转换
 *
 * @author andyron
 * @date 2026/4/17
 */
@Slf4j
public class CommentConverter {

    public static CommentDO toDo(CommentSaveReq req) {
        if (req == null) {
            return null;
        }
        CommentDO commentDO = new CommentDO();
        commentDO.setId(req.getCommentId());
        commentDO.setArticleId(req.getArticleId());
        commentDO.setUserId(req.getUserId());
        commentDO.setContent(req.getCommentContent());
        commentDO.setParentCommentId(req.getParentCommentId() == null ? 0L : req.getParentCommentId());
        commentDO.setTopCommentId(req.getTopCommentId() == null ? 0L : req.getTopCommentId());
        if (req.getHighlight() != null) {
            commentDO.setHighlightInfo(JsonUtil.toStr(req.getHighlight()));
        } else {
            commentDO.setHighlightInfo("{}");
        }
        return commentDO;
    }

    private static <T extends BaseCommentDTO> void parseDto(CommentDO comment, T sub) {
        sub.setCommentId(comment.getId());
        sub.setUserId(comment.getUserId());
        sub.setCommentContent(comment.getContent());
        sub.setCommentTime(comment.getCreateTime().getTime());
        sub.setPraiseCount(0);
        if (StringUtils.isNotBlank(comment.getHighlightInfo())) {
            try {
                sub.setHighlight(JsonUtil.toObj(comment.getHighlightInfo(), HighlightDto.class));
            } catch (Exception e) {
                log.error("反序列化异常~: {}", comment, e);
            }
        }
    }

    public static TopCommentDTO toTopDto(CommentDO commentDO) {
        TopCommentDTO dto = new TopCommentDTO();
        parseDto(commentDO, dto);
        dto.setChildComments(new ArrayList<>());
        return dto;
    }

    public static SubCommentDTO toSubDto(CommentDO comment) {
        SubCommentDTO sub = new SubCommentDTO();
        parseDto(comment, sub);
        sub.setParentCommentId(comment.getParentCommentId());
        return sub;
    }
}
