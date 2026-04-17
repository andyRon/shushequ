package top.andyron.shushequ.api.model.vo.shortlink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短链接返回对象
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkVO {
    /**
     * 短链接URL
     */
    private String shortUrl;

    /**
     * 原始URL
     */
    private String originalUrl;
}