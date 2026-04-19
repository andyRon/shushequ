package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.OperateArticleEnum;
import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.article.*;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleAdminDTO;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.SimpleArticleDTO;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.core.util.NumUtil;
import top.andyron.shushequ.service.article.service.AiSeoService;
import top.andyron.shushequ.service.article.service.ArticleReadService;
import top.andyron.shushequ.service.article.service.ArticleSettingService;
import top.andyron.shushequ.service.article.service.ArticleWriteService;
import top.andyron.shushequ.web.front.search.vo.SearchArticleVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章后台
 *
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@Permission(role = UserRole.LOGIN)
@Tag(name = "文章管理", description = "文章设置管理控制器")
@RequestMapping(path = {"/api/admin/article/", "/admin/article/"})
public class ArticleSettingRestController {

    @Autowired
    private ArticleSettingService articleSettingService;

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private ArticleWriteService articleWriteService;

    @Autowired
    private AiSeoService aiSeoService;

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "save")
    public ResVo<String> save(@RequestBody ArticlePostReq req) {
        if (NumUtil.nullOrZero(req.getArticleId())) {
            // 新增文章
            this.articleWriteService.saveArticle(req, ReqInfoContext.getReqInfo().getUserId());
        } else {
            this.articleWriteService.saveArticle(req, null);
        }
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "update")
    public ResVo<String> update(@RequestBody ArticlePostReq req) {
        articleSettingService.updateArticle(req);
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "operate")
    public ResVo<String> operate(@RequestParam(name = "articleId") Long articleId, @RequestParam(name = "operateType") Integer operateType) {
        OperateArticleEnum operate = OperateArticleEnum.fromCode(operateType);
        if (operate == OperateArticleEnum.EMPTY) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, operateType + "非法");
        }
        articleSettingService.operateArticle(articleId, operate);
        return ResVo.ok();
    }


    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "delete")
    public ResVo<String> delete(@RequestParam(name = "articleId") Long articleId) {
        articleSettingService.deleteArticle(articleId);
        return ResVo.ok();
    }

    // 根据文章id获取文章详情
    @Operation(summary = "根据文章id获取文章详情")
    @GetMapping(path = "detail")
    public ResVo<ArticleDTO> detail(@RequestParam(name = "articleId", required = false) Long articleId) {
        ArticleDTO articleDTO = new ArticleDTO();
        if (articleId != null) {
            // 查询文章详情
            articleDTO = articleReadService.queryDetailArticleInfo(articleId);
        }

        return ResVo.ok(articleDTO);
    }

    @Operation(summary = "获取文章列表")
    @PostMapping(path = "list")
    public ResVo<PageVo<ArticleAdminDTO>> list(@RequestBody SearchArticleReq req) {
        PageVo<ArticleAdminDTO> articleDTOPageVo = articleSettingService.getArticleList(req);
        return ResVo.ok(articleDTOPageVo);
    }

    @Operation(summary = "文章搜索，按照文章标题关键字")
    @GetMapping(path = "query")
    public ResVo<SearchArticleVo> queryArticleList(@RequestParam(name = "key", required = false) String key) {
        List<SimpleArticleDTO> list = articleReadService.querySimpleArticleBySearchKey(key);
        SearchArticleVo vo = new SearchArticleVo();
        vo.setKey(key);
        vo.setItems(list);
        return ResVo.ok(vo);
    }

    @Operation(summary = "AI生成SEO标题和描述")
    @PostMapping(path = "generate/seo")
    public ResVo<AiSeoGenerateRes> generateSeo(@RequestBody AiSeoGenerateReq req) {
        AiSeoGenerateRes response = aiSeoService.generateSeoTitleAndDescription(req);
        return ResVo.ok(response);
    }

    @Operation(summary = "生成文章语义URL")
    @PostMapping(path = "generate/slug")
    public ResVo<String> generateSlug(@RequestBody AiSlugGenerateReq req) {
        return ResVo.ok(articleSettingService.generateUrlSlug(req));
    }

}
