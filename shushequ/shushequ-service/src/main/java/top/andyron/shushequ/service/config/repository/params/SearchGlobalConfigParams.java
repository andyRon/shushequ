package top.andyron.shushequ.service.config.repository.params;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.vo.PageParam;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchGlobalConfigParams extends PageParam {
    // 配置项名称
    private String key;
    // 配置项值
    private String value;
    // 备注
    private String comment;
}
