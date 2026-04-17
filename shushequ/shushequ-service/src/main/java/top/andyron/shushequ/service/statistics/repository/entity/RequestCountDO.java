package top.andyron.shushequ.service.statistics.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.entity.BaseDO;

import java.util.Date;

/**
 * 请求计数表
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("request_count")
public class RequestCountDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 机器IP
     */
    private String host;

    /**
     * 访问计数
     */
    private Integer cnt;

    /**
     * 当前日期
     */
    private Date date;
}
