package top.andyron.shushequ.web.front.user.vo;

import top.andyron.shushequ.api.model.enums.FollowSelectEnum;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.TagSelectDTO;
import top.andyron.shushequ.api.model.vo.user.dto.FollowUserInfoDTO;
import top.andyron.shushequ.api.model.vo.user.dto.UserPayCodeDTO;
import top.andyron.shushequ.api.model.vo.user.dto.UserStatisticInfoDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class UserHomeVo {
    private String homeSelectType;
    private List<TagSelectDTO> homeSelectTags;
    /**
     * 关注列表/粉丝列表
     */
    private PageListVo<FollowUserInfoDTO> followList;
    /**
     * @see FollowSelectEnum#getCode()
     */
    private String followSelectType;
    private List<TagSelectDTO> followSelectTags;
    private UserStatisticInfoDTO userHome;

    /**
     * 文章列表
     */
    private PageListVo<ArticleDTO> homeSelectList;

    /**
     * 收款二维码
     */
    private Map<String, UserPayCodeDTO> payQrCodes;
}
