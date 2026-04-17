package top.andyron.shushequ.service.user.repository.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.andyron.shushequ.api.model.vo.PageParam;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchZsxqWhiteParams extends PageParam {

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 星球编号
     */
    private String starNumber;

    /**
     * 登录用户名
     */
    private String name;

    /**
     * 用户编号
     */
    private String userCode;
    
    /**
     * 是否只查询星球编号不为空的用户
     */
    private Boolean starNumberNotEmpty;
    
    /**
     * 是否只查询最近一周登录的用户
     */
    private Boolean lastLoginWithinWeek;

}