package top.andyron.shushequ.service.article.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.entity.BaseDO;

/**
 * 专栏文章
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("column_article")
public class ColumnArticleDO extends BaseDO {
    private static final long serialVersionUID = -2372103913090667453L;

    /**
     * 专栏
     */
    private Long columnId;

    /**
     * 专栏文章
     */
    private Long articleId;

    /**
     * 专栏文章分组
     */
    private Long groupId;

    /**
     * 顺序，越小越靠前
     */
    private Integer section;

    /**
     * 专栏类型：免费、登录阅读、收费阅读等
     *
     * @see ColumnArticleReadEnum#getRead()
     */
    private Integer readType;
}
