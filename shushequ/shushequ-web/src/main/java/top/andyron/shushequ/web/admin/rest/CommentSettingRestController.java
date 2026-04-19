package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.comment.CommentSaveReq;
import top.andyron.shushequ.api.model.vo.comment.SearchCommentReq;
import top.andyron.shushequ.api.model.vo.comment.dto.CommentAdminDTO;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.service.comment.service.CommentSettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Permission(role = UserRole.LOGIN)
@Tag(name = "评论管理", description = "评论管理控制器")
@RequestMapping(path = {"api/admin/comment/", "admin/comment/"})
public class CommentSettingRestController {

    @Autowired
    private CommentSettingService commentSettingService;

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "评论列表")
    @PostMapping(path = "list")
    public ResVo<PageVo<CommentAdminDTO>> list(@RequestBody SearchCommentReq req) {
        return ResVo.ok(commentSettingService.getCommentList(req));
    }

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "评论详情")
    @GetMapping(path = "detail")
    public ResVo<CommentAdminDTO> detail(@RequestParam(name = "commentId") Long commentId) {
        return ResVo.ok(commentSettingService.getCommentDetail(commentId));
    }

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "保存评论")
    @PostMapping(path = "save")
    public ResVo<String> save(@RequestBody CommentSaveReq req) {
        if (req.getCommentContent() != null) {
            req.setCommentContent(StringEscapeUtils.escapeHtml3(req.getCommentContent()));
        }
        commentSettingService.saveComment(req, ReqInfoContext.getReqInfo().getUserId());
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "删除评论")
    @GetMapping(path = "delete")
    public ResVo<String> delete(@RequestParam(name = "commentId") Long commentId) {
        commentSettingService.deleteComment(commentId);
        return ResVo.ok();
    }
}
