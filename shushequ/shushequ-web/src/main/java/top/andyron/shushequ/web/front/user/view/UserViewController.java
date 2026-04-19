package top.andyron.shushequ.web.front.user.view;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.FollowSelectEnum;
import top.andyron.shushequ.api.model.enums.FollowTypeEnum;
import top.andyron.shushequ.api.model.enums.HomeSelectEnum;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.TagSelectDTO;
import top.andyron.shushequ.api.model.vo.user.dto.FollowUserInfoDTO;
import top.andyron.shushequ.api.model.vo.user.dto.UserStatisticInfoDTO;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.core.util.SpringUtil;
import top.andyron.shushequ.service.article.conveter.PayConverter;
import top.andyron.shushequ.service.article.service.ArticleReadService;
import top.andyron.shushequ.service.user.service.UserRelationService;
import top.andyron.shushequ.service.user.service.UserService;
import top.andyron.shushequ.web.front.user.vo.UserHomeVo;
import top.andyron.shushequ.web.global.BaseViewController;
import top.andyron.shushequ.web.global.SeoInjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * 用户注册、取消，登录、登出
 *
 * @author andyron
 * @date 2026/4/19
 **/
@Controller
@RequestMapping(path = "user")
@Slf4j
public class UserViewController extends BaseViewController {

    @Resource
    private UserService userService;

    @Resource
    private UserRelationService userRelationService;

    @Resource
    private ArticleReadService articleReadService;

    private static final List<String> homeSelectTags = Arrays.asList("article", "read", "follow", "collection");
    private static final List<String> followSelectTags = Arrays.asList("follow", "fans");

    /**
     * 获取用户主页信息，通常只有作者本人才能进入这个页面
     *
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "home")
    public String getUserHome(@RequestParam(name = "userId") Long userId,
                              @RequestParam(name = "homeSelectType", required = false) String homeSelectType,
                              @RequestParam(name = "followSelectType", required = false) String followSelectType,
                              Model model) {
        UserHomeVo vo = new UserHomeVo();
        vo.setHomeSelectType(StringUtils.isBlank(homeSelectType) ? HomeSelectEnum.ARTICLE.getCode() : homeSelectType);
        vo.setFollowSelectType(StringUtils.isBlank(followSelectType) ? FollowTypeEnum.FOLLOW.getCode() : followSelectType);

        UserStatisticInfoDTO userInfo = userService.queryUserInfoWithStatistic(userId);
        vo.setUserHome(userInfo);

        List<TagSelectDTO> homeSelectTags = homeSelectTags(vo.getHomeSelectType(), Objects.equals(userId, ReqInfoContext.getReqInfo().getUserId()));
        vo.setHomeSelectTags(homeSelectTags);

        vo.setPayQrCodes(PayConverter.formatPayCodeInfo(userInfo.getPayCode()));

        userHomeSelectList(vo, userId);
        model.addAttribute("vo", vo);
        SpringUtil.getBean(SeoInjectService.class).initUserSeo(vo);
        return "views/user/index";
    }

    /**
     * 访问其他用户的主页
     *
     * @param userId
     * @param homeSelectType
     * @param followSelectType
     * @param model
     * @return
     */
    @GetMapping(path = "/{userId:\\d+}")
    public String detail(@PathVariable(name = "userId") Long userId, @RequestParam(name = "homeSelectType", required = false) String homeSelectType,
                         @RequestParam(name = "followSelectType", required = false) String followSelectType,
                         Model model) {
        UserHomeVo vo = new UserHomeVo();
        vo.setHomeSelectType(StringUtils.isBlank(homeSelectType) ? HomeSelectEnum.ARTICLE.getCode() : homeSelectType);
        vo.setFollowSelectType(StringUtils.isBlank(followSelectType) ? FollowTypeEnum.FOLLOW.getCode() : followSelectType);

        UserStatisticInfoDTO userInfo = userService.queryUserInfoWithStatistic(userId);
        vo.setUserHome(userInfo);

        List<TagSelectDTO> homeSelectTags = homeSelectTags(vo.getHomeSelectType(), Objects.equals(userId, ReqInfoContext.getReqInfo().getUserId()));
        vo.setHomeSelectTags(homeSelectTags);

        userHomeSelectList(vo, userId);
        model.addAttribute("vo", vo);
        SpringUtil.getBean(SeoInjectService.class).initUserSeo(vo);
        return "views/user/index";
    }

    /**
     * 返回Home页选择列表标签
     *
     * @param selectType
     * @param isAuthor true 表示当前为查看自己的个人主页
     * @return
     */
    private List<TagSelectDTO> homeSelectTags(String selectType, boolean isAuthor) {
        List<TagSelectDTO> tags = new ArrayList<>();
        homeSelectTags.forEach(tag -> {
            if (!isAuthor && "read".equals(tag)) {
                // 只有本人才能看自己的阅读历史
                return;
            }
            TagSelectDTO tagSelectDTO = new TagSelectDTO();
            tagSelectDTO.setSelectType(tag);
            tagSelectDTO.setSelectDesc(HomeSelectEnum.fromCode(tag).getDesc());
            tagSelectDTO.setSelected(selectType.equals(tag));
            tags.add(tagSelectDTO);
        });
        return tags;
    }

    /**
     * 返回关注用户选择列表标签
     *
     * @param selectType
     * @return
     */
    private List<TagSelectDTO> followSelectTags(String selectType) {
        List<TagSelectDTO> tags = new ArrayList<>();
        followSelectTags.forEach(tag -> {
            TagSelectDTO tagSelectDTO = new TagSelectDTO();
            tagSelectDTO.setSelectType(tag);
            tagSelectDTO.setSelectDesc(FollowSelectEnum.fromCode(tag).getDesc());
            tagSelectDTO.setSelected(selectType.equals(tag));
            tags.add(tagSelectDTO);
        });
        return tags;
    }

    /**
     * 返回选择列表
     *
     * @param vo
     * @param userId
     */
    private void userHomeSelectList(UserHomeVo vo, Long userId) {
        PageParam pageParam = PageParam.newPageInstance();
        HomeSelectEnum select = HomeSelectEnum.fromCode(vo.getHomeSelectType());
        if (select == null) {
            return;
        }

        switch (select) {
            case ARTICLE:
            case READ:
            case COLLECTION:
                PageListVo<ArticleDTO> dto = articleReadService.queryArticlesByUserAndType(userId, pageParam, select);
                vo.setHomeSelectList(dto);
                return;
            case FOLLOW:
                // 关注用户与被关注用户
                // 获取选择标签
                List<TagSelectDTO> followSelectTags = followSelectTags(vo.getFollowSelectType());
                vo.setFollowSelectTags(followSelectTags);
                initFollowFansList(vo, userId, pageParam);
                return;
            default:
        }
    }

    private void initFollowFansList(UserHomeVo vo, long userId, PageParam pageParam) {
        PageListVo<FollowUserInfoDTO> followList;
        boolean needUpdateRelation = false;
        if (vo.getFollowSelectType().equals(FollowTypeEnum.FOLLOW.getCode())) {
            followList = userRelationService.getUserFollowList(userId, pageParam);
        } else {
            // 查询粉丝列表时，只能确定粉丝关注了userId，但是不能反向判断，因此需要再更新下映射关系，判断userId是否有关注这个用户
            followList = userRelationService.getUserFansList(userId, pageParam);
            needUpdateRelation = true;
        }

        Long loginUserId = ReqInfoContext.getReqInfo().getUserId();
        if (!Objects.equals(loginUserId, userId) || needUpdateRelation) {
            userRelationService.updateUserFollowRelationId(followList, loginUserId);
        }
        vo.setFollowList(followList);
    }


    @GetMapping("pay")
    @Permission(role = UserRole.LOGIN)
    public String pay() {
        return  "views/user/pay-item";
    }
}
