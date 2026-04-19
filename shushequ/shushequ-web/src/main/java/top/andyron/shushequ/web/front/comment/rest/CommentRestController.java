package top.andyron.shushequ.web.front.comment.rest;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.DocumentTypeEnum;
import top.andyron.shushequ.api.model.enums.OperateTypeEnum;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.comment.CommentSaveReq;
import top.andyron.shushequ.api.model.vo.comment.dto.TopCommentDTO;
import top.andyron.shushequ.api.model.vo.comment.vo.SubCommentListVO;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.core.util.NumUtil;
import top.andyron.shushequ.service.article.conveter.ArticleConverter;
import top.andyron.shushequ.service.article.repository.entity.ArticleDO;
import top.andyron.shushequ.service.article.service.ArticleReadService;
import top.andyron.shushequ.service.comment.repository.entity.CommentDO;
import top.andyron.shushequ.service.comment.service.CommentReadService;
import top.andyron.shushequ.service.comment.service.CommentWriteService;
import top.andyron.shushequ.service.user.service.UserFootService;
import top.andyron.shushequ.web.component.TemplateEngineHelper;
import top.andyron.shushequ.web.front.article.vo.ArticleDetailVo;
import top.andyron.shushequ.web.front.comment.vo.CommentPageVo;
import top.andyron.shushequ.web.front.comment.vo.HighlightCommentVo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 评论
 *
 * @author andyron
 * @date 2026/4/19
 **/
@RestController
@RequestMapping(path = "comment/api")
public class CommentRestController {
    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private CommentReadService commentReadService;

    @Autowired
    private CommentWriteService commentWriteService;

    @Autowired
    private UserFootService userFootService;

    @Autowired
    private TemplateEngineHelper templateEngineHelper;

    /**
     * 评论列表页
     *
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "list")
    public ResVo<List<TopCommentDTO>> list(Long articleId, Long pageNum, Long pageSize) {
        if (NumUtil.nullOrZero(articleId)) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章id为空");
        }
        pageNum = Optional.ofNullable(pageNum).orElse(PageParam.DEFAULT_PAGE_NUM);
        pageSize = Optional.ofNullable(pageSize).orElse(PageParam.DEFAULT_PAGE_SIZE);
        List<TopCommentDTO> result = commentReadService.getArticleComments(articleId, PageParam.newPageInstance(pageNum, pageSize));
        return ResVo.ok(result);
    }

    /**
     * 保存评论
     *
     * @param req
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @PostMapping(path = "post")
    @ResponseBody
    public ResVo<String> save(@RequestBody CommentSaveReq req) {
        if (req.getArticleId() == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章id为空");
        }
        ArticleDO article = articleReadService.queryBasicArticle(req.getArticleId());
        if (article == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章不存在!");
        }

        // 保存评论
        req.setUserId(ReqInfoContext.getReqInfo().getUserId());
        req.setCommentContent(StringEscapeUtils.escapeHtml3(req.getCommentContent()));
        commentWriteService.saveComment(req);

        // 返回新的评论信息，用于实时更新详情也的评论列表
        ArticleDetailVo vo = new ArticleDetailVo();
        vo.setArticle(ArticleConverter.toDto(article));
        // 评论信息
        List<TopCommentDTO> comments = commentReadService.getArticleComments(req.getArticleId(), PageParam.newPageInstance());
        vo.setComments(comments);
        vo.setTopCommentTotal(commentReadService.queryTopCommentCount(req.getArticleId()));

        // 热门评论
        TopCommentDTO hotComment = commentReadService.queryHotComment(req.getArticleId());
        vo.setHotComment(hotComment);
        String content = templateEngineHelper.render("views/article-detail/comment/index", vo);
        return ResVo.ok(content);
    }


    /**
     * 划线评论
     *
     * @param req
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @PostMapping(path = "highlightComment")
    @ResponseBody
    public ResVo<HighlightCommentVo> highlightComment(@RequestBody CommentSaveReq req) {
        if (req.getArticleId() == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章id为空");
        }
        ArticleDO article = articleReadService.queryBasicArticle(req.getArticleId());
        if (article == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章不存在!");
        }

        // 保存评论
        req.setUserId(ReqInfoContext.getReqInfo().getUserId());
        req.setCommentContent(StringEscapeUtils.escapeHtml3(req.getCommentContent()));
        Long commentId = commentWriteService.saveComment(req);
        TopCommentDTO comments = commentReadService.queryTopComments(commentId);
        String content = templateEngineHelper.render("components/comment/comment-highlight", comments);
        HighlightCommentVo vo = new HighlightCommentVo();
        vo.setCommentId(commentId);
        vo.setHtml(content);
        return ResVo.ok(vo);
    }

    /**
     * 获取文章的顶级评论列表
     *
     * @param commentId
     * @return
     */
    @Permission(role = UserRole.ALL)
    @GetMapping(path = "listTopComment")
    @ResponseBody
    public ResVo<String> listTopComment(Long commentId) {
        TopCommentDTO comments = commentReadService.queryTopComments(commentId);
        String content = templateEngineHelper.render("components/comment/comment-highlight", comments);
        return ResVo.ok(content);
    }

    /**
     * 分页加载一级评论 html 片段
     *
     * @param articleId 文章ID
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 评论 html 片段
     */
    @Permission(role = UserRole.ALL)
    @GetMapping(path = "listPageHtml")
    @ResponseBody
    public ResVo<CommentPageVo> listPageHtml(Long articleId, Long pageNum, Long pageSize) {
        if (NumUtil.nullOrZero(articleId)) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章id为空");
        }

        pageNum = Optional.ofNullable(pageNum).orElse(PageParam.DEFAULT_PAGE_NUM);
        pageSize = Optional.ofNullable(pageSize).orElse(PageParam.DEFAULT_PAGE_SIZE);

        ArticleDO article = articleReadService.queryBasicArticle(articleId);
        if (article == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "文章不存在!");
        }

        List<TopCommentDTO> comments = commentReadService.getArticleComments(articleId, PageParam.newPageInstance(pageNum, pageSize));
        ArticleDetailVo renderVo = new ArticleDetailVo();
        renderVo.setArticle(ArticleConverter.toDto(article));
        renderVo.setComments(comments);

        CommentPageVo result = new CommentPageVo();
        result.setHtml(templateEngineHelper.render("components/comment/comment-page-items", renderVo));
        int topCommentTotal = commentReadService.queryTopCommentCount(articleId);
        result.setHasMore(pageNum * pageSize < topCommentTotal);
        result.setNextPageNum(pageNum + 1);
        return ResVo.ok(result);
    }

    /**
     * 分页加载子评论
     *
     * @param topCommentId 一级评论ID
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 子评论列表
     */
    @Permission(role = UserRole.ALL)
    @GetMapping(path = "subComments")
    @ResponseBody
    public ResVo<SubCommentListVO> getSubComments(
            @RequestParam Long topCommentId,
            @RequestParam Long pageNum,
            @RequestParam Long pageSize) {
        if (NumUtil.nullOrZero(topCommentId)) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "一级评论ID为空");
        }
        pageNum = Optional.ofNullable(pageNum).orElse(PageParam.DEFAULT_PAGE_NUM);
        pageSize = Optional.ofNullable(pageSize).orElse(PageParam.DEFAULT_PAGE_SIZE);
        SubCommentListVO result = commentReadService.getSubComments(topCommentId, PageParam.newPageInstance(pageNum, pageSize));
        return ResVo.ok(result);
    }

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @RequestMapping(path = "delete")
    public ResVo<Boolean> delete(Long commentId) {
        commentWriteService.deleteComment(commentId, ReqInfoContext.getReqInfo().getUserId());
        return ResVo.ok(true);
    }

    /**
     * 收藏、点赞等相关操作
     *
     * @param commendId
     * @param type      取值来自于 OperateTypeEnum#code
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "favor")
    public ResVo<Boolean> favor(@RequestParam(name = "commentId") Long commendId,
                                @RequestParam(name = "type") Integer type) {
        OperateTypeEnum operate = OperateTypeEnum.fromCode(type);
        if (operate == OperateTypeEnum.EMPTY) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, type + "非法");
        }

        // 要求文章必须存在
        CommentDO comment = commentReadService.queryComment(commendId);
        if (comment == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "评论不存在!");
        }

        userFootService.favorArticleComment(DocumentTypeEnum.COMMENT,
                commendId,
                comment.getUserId(),
                ReqInfoContext.getReqInfo().getUserId(),
                operate);
        return ResVo.ok(true);
    }

}
