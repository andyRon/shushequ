package top.andyron.shushequ.service.sensitive.service;

/**
 * 敏感词处理豁免服务
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface SensitiveBypassService {

    /**
     * 指定用户发布的内容是否跳过敏感词处理
     *
     * @param userId 用户id
     * @return true 表示跳过敏感词处理
     */
    boolean shouldBypassByUserId(Long userId);
}
