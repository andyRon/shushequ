package top.andyron.shushequ.service.article.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.andyron.shushequ.api.model.entity.BaseDO;

/**
 * fixme 访问计数，后续改用redis替换
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("read_count")
public class ReadCountDO extends BaseDO {
    /**
     * 文档ID（文章/评论）
     */
    private Long documentId;

    /**
     * 文档类型：1-文章，2-评论
     */
    private Integer documentType;

    /**
     * 计数
     */
    private Integer cnt;

}
