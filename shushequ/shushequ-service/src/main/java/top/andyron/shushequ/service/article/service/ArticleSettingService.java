package top.andyron.shushequ.service.article.service;


import top.andyron.shushequ.api.model.enums.OperateArticleEnum;
import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.article.AiSlugGenerateReq;
import top.andyron.shushequ.api.model.vo.article.ArticlePostReq;
import top.andyron.shushequ.api.model.vo.article.SearchArticleReq;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleAdminDTO;

/**
 * 文章后台接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ArticleSettingService {

    /**
     * 更新文章
     *
     * @param req
     */
    void updateArticle(ArticlePostReq req);

    /**
     * AI生成文章语义URL
     *
     * @param req
     * @return
     */
    String generateUrlSlug(AiSlugGenerateReq req);

    /**
     * 获取文章列表
     *
     * @param req
     * @return
     */
    PageVo<ArticleAdminDTO> getArticleList(SearchArticleReq req);

    /**
     * 删除文章
     *
     * @param articleId
     */
    void deleteArticle(Long articleId);

    /**
     * 操作文章
     *
     * @param articleId
     * @param operate
     */
    void operateArticle(Long articleId, OperateArticleEnum operate);
}
