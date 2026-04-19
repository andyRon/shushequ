package top.andyron.shushequ.web.front.article.view;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.ArticleReadTypeEnum;
import top.andyron.shushequ.api.model.enums.column.ColumnArticleReadEnum;
import top.andyron.shushequ.api.model.enums.column.ColumnStatusEnum;
import top.andyron.shushequ.api.model.enums.column.ColumnTypeEnum;
import top.andyron.shushequ.api.model.enums.user.UserAIStatEnum;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.article.dto.*;
import top.andyron.shushequ.api.model.vo.comment.dto.TopCommentDTO;
import top.andyron.shushequ.api.model.vo.recommend.SideBarDTO;
import top.andyron.shushequ.core.util.MarkdownConverter;
import top.andyron.shushequ.core.util.SpringUtil;
import top.andyron.shushequ.core.util.StrUtil;
import top.andyron.shushequ.service.article.repository.entity.ColumnArticleDO;
import top.andyron.shushequ.service.article.service.ArticlePayService;
import top.andyron.shushequ.service.article.service.ArticleReadService;
import top.andyron.shushequ.service.article.service.ColumnService;
import top.andyron.shushequ.service.comment.service.CommentReadService;
import top.andyron.shushequ.service.sidebar.service.SidebarService;
import top.andyron.shushequ.web.config.GlobalViewConfig;
import top.andyron.shushequ.web.front.article.vo.ColumnVo;
import top.andyron.shushequ.web.global.SeoInjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 专栏入口
 *
 * @author andyron
 * @date 2026/4/19
 */
@Controller
@RequestMapping(path = "column")
public class ColumnViewController {
    @Autowired
    private ColumnService columnService;
    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private CommentReadService commentReadService;

    @Autowired
    private SidebarService sidebarService;

    @Resource
    private GlobalViewConfig globalViewConfig;
    @Autowired
    private ArticlePayService articlePayService;

    /**
     * 专栏主页，展示专栏列表
     *
     * @param model
     * @return
     */
    @GetMapping(path = {"list", "/", "", "home"})
    public String list(Model model) {
        PageListVo<ColumnDTO> columns = columnService.listColumn(PageParam.newPageInstance(1L, 6L));
        List<SideBarDTO> sidebars = sidebarService.queryColumnSidebarList();
        ColumnVo vo = new ColumnVo();
        vo.setColumns(columns);
        vo.setSideBarItems(sidebars);
        model.addAttribute("vo", vo);
        return "views/column-home/index";
    }

    /**
     * 专栏详情
     *
     * @param columnId
     * @return
     */
    @GetMapping(path = "{columnId}")
    public String column(@PathVariable("columnId") Long columnId, Model model) {
        ColumnDTO dto = columnService.queryColumnInfo(columnId);
        Long loginUserId = ReqInfoContext.getReqInfo().getUserId();
        
        // 教程未发布 && 不是作者本人 → 拒绝访问
        if (dto.getState() == ColumnStatusEnum.OFFLINE.getCode()) {
            if (loginUserId == null || !loginUserId.equals(dto.getAuthor())) {
                model.addAttribute("toast", "教程未发布,暂时无法访问");
                return "views/error/403";
            }
        }
        
        model.addAttribute("vo", dto);
        return "/views/column-index/index";
    }


    /**
     * 专栏的文章阅读界面
     *
     * @param columnId 专栏id
     * @param section  节数，从1开始
     * @param model
     * @return
     */
    @GetMapping(path = "{columnId}/{section}")
    public String articles(@PathVariable("columnId") Long columnId, @PathVariable("section") Integer section, Model model) {
        if (section <= 0) section = 1;
        // 查询专栏
        ColumnDTO column = columnService.queryBasicColumnInfo(columnId);
        Long loginUserId = ReqInfoContext.getReqInfo().getUserId();
        
        // 教程未发布 && 不是作者本人 → 拒绝访问
        if (column.getState() == ColumnStatusEnum.OFFLINE.getCode()) {
            if (loginUserId == null || !loginUserId.equals(column.getAuthor())) {
                model.addAttribute("toast", "教程未发布,暂时无法访问");
                return "views/error/403";
            }
        }

        ColumnArticleDO columnArticle = columnService.queryColumnArticle(columnId, section);
        Long articleId = columnArticle.getArticleId();
        // 文章信息
        ArticleDTO articleDTO = articleReadService.queryFullArticleInfo(articleId, ReqInfoContext.getReqInfo().getUserId());
        // 返回html格式的文档内容
        articleDTO.setContent(MarkdownConverter.markdownToHtml(articleDTO.getContent()));
        // 评论信息
        List<TopCommentDTO> comments = commentReadService.getArticleComments(articleId, PageParam.newPageInstance());
        Integer topCommentTotal = commentReadService.queryTopCommentCount(articleId);

        // 热门评论
        TopCommentDTO hotComment = commentReadService.queryHotComment(articleId);

        List<TopCommentDTO> highlightComment = commentReadService.queryHighlightComments(articleId);

        // 文章列表
        List<SimpleArticleDTO> articles = columnService.queryColumnArticles(columnId);

        ColumnArticlesDTO vo = new ColumnArticlesDTO();
        vo.setArticle(articleDTO);
        vo.setComments(comments);
        vo.setTopCommentTotal(topCommentTotal);
        vo.setHotComment(hotComment);
        vo.setHighlightComments(highlightComment);
        vo.setColumn(columnId);
        vo.setSection(section);
        vo.setArticleList(articles);

        ArticleOtherDTO other = new ArticleOtherDTO();

        // 教程类型
        updateReadType(other, column, articleDTO, ColumnArticleReadEnum.valueOf(columnArticle.getReadType()));


        // 把是文章翻页的参数封装到这里
        // prev 的 href 和 是否显示的 flag
        ColumnArticleFlipDTO flip = new ColumnArticleFlipDTO();
        flip.setPrevHref("/column/" + columnId + "/" + (section - 1));
        flip.setPrevShow(section > 1);
        // next 的 href 和 是否显示的 flag
        flip.setNextHref("/column/" + columnId + "/" + (section + 1));
        flip.setNextShow(section < articles.size());
        other.setFlip(flip);

        // 放入 model 中
        vo.setOther(other);

        // 打赏用户列表
        if (Objects.equals(articleDTO.getReadType(), ArticleReadTypeEnum.PAY_READ.getType())) {
            vo.setPayUsers(articlePayService.queryPayUsers(articleId));
        } else {
            vo.setPayUsers(Collections.emptyList());
        }
        model.addAttribute("vo", vo);

        SpringUtil.getBean(SeoInjectService.class).initColumnSeo(vo, column);
        return "views/column-detail/index";
    }

    /**
     * 对于要求登录阅读的文章进行进行处理
     *
     * @param vo
     * @param column
     * @param articleDTO
     */
    private void updateReadType(ArticleOtherDTO vo, ColumnDTO column, ArticleDTO articleDTO, ColumnArticleReadEnum articleReadEnum) {
        Long loginUser = ReqInfoContext.getReqInfo().getUserId();
        if (loginUser != null && loginUser.equals(articleDTO.getAuthor())) {
            vo.setReadType(ColumnTypeEnum.FREE.getType());
            return;
        }

        if (articleReadEnum == ColumnArticleReadEnum.COLUMN_TYPE) {
            // 专栏中的文章，没有特殊指定时，直接沿用专栏的规则
            if (column.getType() == ColumnTypeEnum.TIME_FREE.getType()) {
                long now = System.currentTimeMillis();
                if (now > column.getFreeEndTime() || now < column.getFreeStartTime()) {
                    vo.setReadType(ColumnTypeEnum.LOGIN.getType());
                } else {
                    vo.setReadType(ColumnTypeEnum.FREE.getType());
                }
            } else {
                vo.setReadType(column.getType());
            }
        } else {
            // 直接使用文章特殊设置的规则
            vo.setReadType(articleReadEnum.getRead());
        }
        // 如果是星球 or 登录阅读时，不返回全量的文章内容
        articleDTO.setContent(trimContent(vo.getReadType(), articleDTO.getContent()));
        // fix 关于 cover 封面，文章详情的前端已经不显示了，这里直接删除
    }

    /**
     * 文章内容隐藏
     *
     * @param readType
     * @param content
     * @return
     */
    private String trimContent(int readType, String content) {
        if (readType == ColumnTypeEnum.STAR_READ.getType()) {
            // 判断登录用户是否绑定了星球，如果是，则直接阅读完整的专栏内容
            if (ReqInfoContext.getReqInfo().getUser() != null && ReqInfoContext.getReqInfo().getUser().getStarStatus() == UserAIStatEnum.FORMAL) {
                return content;
            }

            // 如果没有绑定星球，则返回 10% 的内容
            int count = Integer.parseInt(globalViewConfig.getZsxqArticleReadCount());
            return StrUtil.safeSubstringHtml(content, content.length() * count / 100);
        }

        if ((readType == ColumnTypeEnum.LOGIN.getType() && ReqInfoContext.getReqInfo().getUserId() == null)) {
            // 如果是登录阅读，但是用户没有登录，则返回 20% 的内容
            int count = Integer.parseInt(globalViewConfig.getNeedLoginArticleReadCount());
            return StrUtil.safeSubstringHtml(content, content.length() * count / 100);
        }

        return content;
    }
}
