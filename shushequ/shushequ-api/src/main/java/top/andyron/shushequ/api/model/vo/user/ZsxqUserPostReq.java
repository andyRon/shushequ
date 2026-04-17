package top.andyron.shushequ.api.model.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class ZsxqUserPostReq implements Serializable {
    // id
    private Long id;
    // 用户名
    private String userCode;
    // 用户昵称
    private String name;
    // 星球编号
    private String starNumber;
    // 星球过期时间 (支持日期字符串格式)
    private String expireTime;
}
