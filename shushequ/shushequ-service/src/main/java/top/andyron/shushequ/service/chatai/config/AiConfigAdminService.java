package top.andyron.shushequ.service.chatai.config;

import top.andyron.shushequ.api.model.vo.ai.config.AiConfigAdminReq;
import top.andyron.shushequ.api.model.vo.ai.config.AiConfigTestReq;
import top.andyron.shushequ.api.model.vo.ai.config.dto.AiConfigAdminDTO;
import top.andyron.shushequ.api.model.vo.ai.config.dto.AiConfigTestDTO;

/**
 * AI 配置管理服务
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface AiConfigAdminService {
    /**
     * 读取当前生效中的 AI 配置
     *
     * @return AI 配置详情
     */
    AiConfigAdminDTO getConfig();

    /**
     * 保存 AI 配置
     *
     * @param req 配置内容
     */
    void save(AiConfigAdminReq req);

    /**
     * 测试指定模型连通性
     *
     * @param req 测试请求
     * @return 测试结果
     */
    AiConfigTestDTO test(AiConfigTestReq req);
}
