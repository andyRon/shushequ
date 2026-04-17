package top.andyron.shushequ.service.article.service;


import top.andyron.shushequ.api.model.vo.article.AiSeoGenerateReq;
import top.andyron.shushequ.api.model.vo.article.AiSeoGenerateRes;

/**
 * AI SEO生成服务
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface AiSeoService {
    
    /**
     * 根据短标题和正文内容生成SEO优化的长标题和描述
     *
     * @param req 包含短标题和正文内容的请求
     * @return AI生成的SEO标题和描述
     */
    AiSeoGenerateRes generateSeoTitleAndDescription(AiSeoGenerateReq req);
}
