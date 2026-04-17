package top.andyron.shushequ.service.shortlink.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.andyron.shushequ.api.model.entity.BaseDO;

/**
 * 短链接记录数据库对象
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("short_link_record")
public class ShortLinkRecordDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 短链接代码
     */
    private String shortCode;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 访问时间
     */
    private Long accessTime;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 登录方式 （如：微信、QQ、微博等）。
     */
    private String loginMethod;

    /**
     * 访问来源（如：网页、移动端等）。
     */
    private String accessSource;
}