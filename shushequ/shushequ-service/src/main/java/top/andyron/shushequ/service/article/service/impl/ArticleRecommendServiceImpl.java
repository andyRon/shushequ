package top.andyron.shushequ.service.article.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.service.article.repository.dao.ArticleDao;
import top.andyron.shushequ.service.article.repository.dao.ArticleTagDao;
import top.andyron.shushequ.service.article.repository.entity.ArticleDO;
import top.andyron.shushequ.service.article.repository.entity.ArticleTagDO;
import top.andyron.shushequ.service.article.service.ArticleReadService;
import top.andyron.shushequ.service.article.service.ArticleRecommendService;
import top.andyron.shushequ.service.service.SidebarService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class ArticleRecommendServiceImpl implements ArticleRecommendService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTagDao articleTagDao;
    @Autowired
    private ArticleReadService articleReadService;
    @Autowired
    private SidebarService sidebarService;

    /**
     * 查询文章关联推荐列表
     *
     * @param articleId
     * @param page
     * @return
     */
    @Override
    public PageListVo<ArticleDTO> relatedRecommend(Long articleId, PageParam page) {
        ArticleDO article = articleDao.getById(articleId);
        if (article == null) {
            return PageListVo.emptyVo();
        }
        List<Long> tagIds = articleTagDao.listArticleTags(articleId).stream()
                .map(ArticleTagDO::getTagId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(tagIds)) {
            return PageListVo.emptyVo();
        }

        List<ArticleDO> recommendArticles = articleDao.listRelatedArticlesOrderByReadCount(article.getCategoryId(), tagIds, page);
        if (recommendArticles.removeIf(s -> s.getId().equals(articleId))) {
            // 移除推荐列表中的当前文章
            page.setPageSize(page.getPageSize() - 1);
        }
        return articleReadService.buildArticleListVo(recommendArticles, page.getPageSize());
    }
}
