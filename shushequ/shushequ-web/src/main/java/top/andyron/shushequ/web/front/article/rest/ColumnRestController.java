package top.andyron.shushequ.web.front.article.rest;

import top.andyron.shushequ.api.model.vo.NextPageHtmlVo;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.article.dto.ColumnDTO;
import top.andyron.shushequ.api.model.vo.article.dto.SimpleArticleDTO;
import top.andyron.shushequ.service.article.service.ColumnService;
import top.andyron.shushequ.web.component.TemplateEngineHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@RequestMapping(path = "column/api")
public class ColumnRestController {
    @Autowired
    private ColumnService columnService;

    @Autowired
    private TemplateEngineHelper templateEngineHelper;

    /**
     * 翻页的专栏列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping(path = "list")
    public ResVo<NextPageHtmlVo> list(@RequestParam(name = "page") Long page,
                                      @RequestParam(name = "size", required = false) Long size) {
        if (page <= 0) {
            page = 1L;
        }
        size = Optional.ofNullable(size).orElse(PageParam.DEFAULT_PAGE_SIZE);
        size = Math.min(size, PageParam.DEFAULT_PAGE_SIZE);
        PageListVo<ColumnDTO> list = columnService.listColumn(PageParam.newPageInstance(page, size));

        String html = templateEngineHelper.renderToVo("biz/column/list", "columns", list);
        return ResVo.ok(new NextPageHtmlVo(html, list.getHasMore()));
    }

    /**
     * 详情页的菜单栏(即专栏的文章列表)
     *
     * @param columnId
     * @return
     */
    @GetMapping(path = "menu/{column}")
    public ResVo<NextPageHtmlVo> columnMenus(@PathVariable("column") Long columnId) {
        List<SimpleArticleDTO> articleList = columnService.queryColumnArticles(columnId);
        String html = templateEngineHelper.renderToVo("biz/column/menus", "menu", articleList);
        return ResVo.ok(new NextPageHtmlVo(html, false));
    }
}
