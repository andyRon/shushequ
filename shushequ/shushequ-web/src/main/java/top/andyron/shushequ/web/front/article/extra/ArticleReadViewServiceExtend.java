package top.andyron.shushequ.web.front.article.extra;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.ArticleReadTypeEnum;
import top.andyron.shushequ.api.model.enums.user.UserAIStatEnum;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.api.model.vo.user.dto.BaseUserInfoDTO;
import top.andyron.shushequ.service.article.service.ArticlePayService;
import top.andyron.shushequ.web.config.GlobalViewConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * 文章阅读的扩展服务支撑
 * - 用于控制文章阅读模式
 *
 * @author andyron
 * @date 2026/4/19
 */
@Service
public class ArticleReadViewServiceExtend {
    @Autowired
    private GlobalViewConfig globalViewConfig;
    @Autowired
    private ArticlePayService articlePayService;


    public String formatArticleReadType(ArticleDTO article) {
        ArticleReadTypeEnum readType = ArticleReadTypeEnum.typeOf(article.getReadType());
        if (readType != null && readType != ArticleReadTypeEnum.NORMAL) {
            BaseUserInfoDTO user = ReqInfoContext.getReqInfo().getUser();
            if (readType == ArticleReadTypeEnum.STAR_READ) {
                // 星球用户阅读
                return mark(article, () -> user != null && (user.getUserId().equals(article.getAuthor())
                                || user.getStarStatus() == UserAIStatEnum.FORMAL),
                        globalViewConfig::getZsxqArticleReadCount);
            } else if (readType == ArticleReadTypeEnum.PAY_READ) {
                // 付费阅读
                return mark(article, () -> user != null && (user.getUserId().equals(article.getAuthor())
                                || articlePayService.hasPayed(article.getArticleId(), user.getUserId())),
                        globalViewConfig::getNeedPayArticleReadCount);
            } else if (readType == ArticleReadTypeEnum.LOGIN) {
                // 登录阅读
                return mark(article, () -> user != null, globalViewConfig::getNeedLoginArticleReadCount);
            }
        }

        article.setCanRead(true);
        return article.getContent();
    }

    private String mark(ArticleDTO article, Supplier<Boolean> condition, Supplier<String> percent) {
        if (condition.get()) {
            // 可以阅读
            article.setCanRead(true);
            return article.getContent();
        } else {
            // 不能阅读
            article.setCanRead(false);
            return article.getContent()
                    .substring(0, (int) (article.getContent().length() * Float.parseFloat(percent.get()) / 100));
        }
    }
}
