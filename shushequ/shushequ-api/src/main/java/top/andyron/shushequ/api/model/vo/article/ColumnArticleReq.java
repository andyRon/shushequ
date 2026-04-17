package top.andyron.shushequ.api.model.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 保存Column文章请求参数
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class ColumnArticleReq implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 专栏ID
     */
    private Long columnId;


    /**
     * 专栏分组id
     */
    private Long groupId;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 文章排序
     */
    private Integer sort;

    /**
     * 教程标题
     */
    private String shortTitle;

    /**
     * 阅读方式
     */
    private Integer read;
}
