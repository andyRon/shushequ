package top.andyron.shushequ.service.service;


import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.config.SearchSensitiveWordHitReq;
import top.andyron.shushequ.api.model.vo.config.SensitiveWordConfigReq;
import top.andyron.shushequ.api.model.vo.config.dto.SensitiveWordConfigDTO;
import top.andyron.shushequ.api.model.vo.config.dto.SensitiveWordHitDTO;

/**
 * 敏感词后台管理服务
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface SensitiveAdminService {

    /**
     * 查询敏感词配置
     *
     * @return 配置详情
     */
    SensitiveWordConfigDTO getConfig();

    /**
     * 分页查询命中统计
     *
     * @param req 查询条件
     * @return 分页结果
     */
    PageVo<SensitiveWordHitDTO> getHitWordPage(SearchSensitiveWordHitReq req);

    /**
     * 保存敏感词配置
     *
     * @param req 配置请求
     */
    void saveConfig(SensitiveWordConfigReq req);

    /**
     * 清除某个敏感词的命中统计
     *
     * @param word 敏感词
     */
    void clearHitWord(String word);
}
