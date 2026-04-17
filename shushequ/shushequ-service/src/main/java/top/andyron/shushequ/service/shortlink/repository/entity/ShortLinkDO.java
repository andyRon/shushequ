package top.andyron.shushequ.service.shortlink.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.andyron.shushequ.api.model.entity.BaseDO;

/**
 * 短链接数据库对象
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("short_link")
public class ShortLinkDO extends BaseDO {


    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 原始URL
     */
    private String originalUrl;

    /**
     * 短链接代码
     */
    private String shortCode;

    /**
     * 删除标记
     */
    private Integer deleted;


}