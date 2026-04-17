package top.andyron.shushequ.api.model.vo.config;

import lombok.Data;

/**
 * 敏感词操作请求
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SensitiveWordOperateReq {
    /**
     * 目标词
     */
    private String word;
}
