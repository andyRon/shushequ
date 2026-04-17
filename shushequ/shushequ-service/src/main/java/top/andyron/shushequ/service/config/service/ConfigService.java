package top.andyron.shushequ.service.config.service;

import top.andyron.shushequ.api.model.enums.ConfigTypeEnum;
import top.andyron.shushequ.api.model.vo.banner.dto.ConfigDTO;

import java.util.List;

/**
 * Banner前台接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ConfigService {

    /**
     * 获取 Banner 列表
     *
     * @param configTypeEnum
     * @return
     */
    List<ConfigDTO> getConfigList(ConfigTypeEnum configTypeEnum);

    /**
     * 阅读次数+1
     *
     * @param configId
     * @param extra
     */
    void updateVisit(long configId, String extra);
}
