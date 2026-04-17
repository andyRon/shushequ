package top.andyron.shushequ.api.model.vo.user;

import lombok.Data;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class SearchZsxqUserReq {
    // 用户昵称
    private String name;
    // 星球编号
    private String starNumber;
    // 用户登录名
    private String userCode;

    private Integer state;
    
    // 是否只查询星球编号不为空的用户
    private Boolean starNumberNotEmpty;
    
    // 是否只查询最近一周登录的用户
    private Boolean lastLoginWithinWeek;
    
    // 分页
    private Long pageNumber;
    private Long pageSize;
}
