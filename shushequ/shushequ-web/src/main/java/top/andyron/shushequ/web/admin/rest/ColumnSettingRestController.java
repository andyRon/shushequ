package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.article.*;
import top.andyron.shushequ.api.model.vo.article.dto.ColumnArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.ColumnArticleGroupDTO;
import top.andyron.shushequ.api.model.vo.article.dto.ColumnDTO;
import top.andyron.shushequ.api.model.vo.article.dto.SimpleColumnDTO;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.service.article.repository.entity.ArticleDO;
import top.andyron.shushequ.service.article.service.ArticleReadService;
import top.andyron.shushequ.service.article.service.ColumnSettingService;
import top.andyron.shushequ.service.image.service.ImageService;
import top.andyron.shushequ.web.front.search.vo.SearchColumnVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专栏后台
 *
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@Slf4j
@Permission(role = UserRole.LOGIN)
@Tag(name = "专栏管理", description = "专栏及专栏文章管理控制器")
@RequestMapping(path = {"api/admin/column/", "admin/column/"})
public class ColumnSettingRestController {

    @Autowired
    private ColumnSettingService columnSettingService;

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private ImageService imageService;

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "saveColumn")
    public ResVo<String> saveColumn(@RequestBody ColumnReq req) {
        columnSettingService.saveColumn(req);
        return ResVo.ok();
    }

    /**
     * 维护专栏的分组情况
     *
     * @param req
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "saveColumnGroup")
    public ResVo<Boolean> saveColumnArticleGroup(@RequestBody ColumnArticleGroupReq req) {
        columnSettingService.saveColumnArticleGroup(req);
        return ResVo.ok(true);
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "saveColumnArticle")
    public ResVo<String> saveColumnArticle(@RequestBody ColumnArticleReq req) {

        // 要求文章必须存在，且已经发布
        ArticleDO articleDO = articleReadService.queryBasicArticle(req.getArticleId());
        if (articleDO == null) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "教程对应的文章不存在!");
        }

        columnSettingService.saveColumnArticle(req);
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "deleteColumn")
    public ResVo<String> deleteColumn(@RequestParam(name = "columnId") Long columnId) {
        columnSettingService.deleteColumn(columnId);
        return ResVo.ok();
    }

    /**
     * 删除专栏文章分组
     *
     * @param groupId 分组id
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "deleteColumnGroup")
    public ResVo<Boolean> deleteColumnGroup(@RequestParam(name = "groupId") Long groupId) {
        boolean ans = columnSettingService.deleteColumnGroup(groupId);
        return ResVo.ok(ans);
    }

    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "deleteColumnArticle")
    public ResVo<String> deleteColumnArticle(@RequestParam(name = "id") Long id) {
        columnSettingService.deleteColumnArticle(id);
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "sortColumnArticleApi")
    public ResVo<String> sortColumnArticleApi(@RequestBody SortColumnArticleReq req) {
        columnSettingService.sortColumnArticleApi(req);
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "sortColumnArticleByIDApi")
    public ResVo<String> sortColumnArticleByIDApi(@RequestBody SortColumnArticleByIDReq req) {
        columnSettingService.sortColumnArticleByIDApi(req);
        return ResVo.ok();
    }


    /**
     * 移动专栏中教程或者分组的位置
     * @param req 请求参数
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "moveColumnArticleOrGroup")
    public ResVo<Boolean> moveColumnArticleOrGroup(@RequestBody MoveColumnArticleOrGroupReq req) {
        columnSettingService.moveColumnArticleOrGroup(req);
        return ResVo.ok(true);
    }

    @Operation(summary = "获取教程列表")
    @PostMapping(path = "list")
    public ResVo<PageVo<ColumnDTO>> list(@RequestBody SearchColumnReq req) {
        PageVo<ColumnDTO> columnDTOPageVo = columnSettingService.getColumnList(req);
        return ResVo.ok(columnDTOPageVo);
    }


    @Operation(summary = "获取教程分组列表")
    @GetMapping(path = "listGroups")
    public ResVo<List<ColumnArticleGroupDTO>> listGroups(@RequestParam("columnId") Long columnId) {
        List<ColumnArticleGroupDTO> list = columnSettingService.getColumnGroups(columnId);
        return ResVo.ok(list);
    }

    /**
     * 获取教程配套的文章列表
     * <p>
     * 请求参数有教程名、文章名
     * 返回教程配套的文章列表
     *
     * @return
     */
    @PostMapping(path = "listColumnArticle")
    public ResVo<PageVo<ColumnArticleDTO>> listColumnArticle(@RequestBody SearchColumnArticleReq req) {
        PageVo<ColumnArticleDTO> vo = columnSettingService.getColumnArticleList(req);
        return ResVo.ok(vo);
    }


    /**
     * 教程的文章，根据分组进行汇聚展示
     *
     * @param columnId
     * @return
     */
    @GetMapping(path = "listColumnByGroup")
    public ResVo<List<ColumnArticleGroupDTO>> listColumnArticlesByGroup(@RequestParam("columnId") Long columnId) {
        List<ColumnArticleGroupDTO> list = columnSettingService.getColumnGroupAndArticles(columnId);
        return ResVo.ok(list);
    }


    @Operation(summary = "专栏搜索")
    @GetMapping(path = "query")
    public ResVo<SearchColumnVo> query(@RequestParam(name = "key", required = false) String key) {
        List<SimpleColumnDTO> list = columnSettingService.listSimpleColumnBySearchKey(key);
        SearchColumnVo vo = new SearchColumnVo();
        vo.setKey(key);
        vo.setItems(list);
        return ResVo.ok(vo);
    }
}
