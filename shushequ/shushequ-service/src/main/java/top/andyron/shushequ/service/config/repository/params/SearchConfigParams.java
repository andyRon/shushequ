package top.andyron.shushequ.service.config.repository.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.vo.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchConfigParams extends PageParam {
    // 类型
    private Integer type;
    // 名称
    private String name;
}
