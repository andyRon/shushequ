package top.andyron.shushequ.service.config.service;

import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.config.GlobalConfigReq;
import top.andyron.shushequ.api.model.vo.config.SearchGlobalConfigReq;
import top.andyron.shushequ.api.model.vo.config.dto.GlobalConfigDTO;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface GlobalConfigService {
    PageVo<GlobalConfigDTO> getList(SearchGlobalConfigReq req);

    void save(GlobalConfigReq req);

    void delete(Long id);

    /**
     * 添加敏感词白名单
     *
     * @param word
     */
    void addSensitiveWhiteWord(String word);
}
