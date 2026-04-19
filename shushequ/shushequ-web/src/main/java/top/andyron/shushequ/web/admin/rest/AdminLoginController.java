package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.api.model.vo.user.dto.BaseUserInfoDTO;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.core.util.SessionUtil;
import top.andyron.shushequ.service.user.service.AuthorWhiteListService;
import top.andyron.shushequ.service.user.service.LoginService;
import top.andyron.shushequ.service.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 文章后台
 *
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@Tag(name = "后台登录", description = "后台登录登出管理控制器")
@RequestMapping(path = {"/api/admin", "/admin"})
public class AdminLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginOutService;

    @Autowired
    private AuthorWhiteListService articleWhiteListService;

    /**
     * 后台用户名 & 密码的方式登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = {"login"})
    public ResVo<BaseUserInfoDTO> login(HttpServletRequest request,
                                        HttpServletResponse response) {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String session = loginOutService.loginByUserPwd(username, pwd);
        if (StringUtils.isNotBlank(session)) {
            // cookie中写入用户登录信息
            response.addCookie(SessionUtil.newCookie(LoginService.SESSION_KEY, session));
            return ResVo.ok(userService.queryBasicUserInfo(ReqInfoContext.getReqInfo().getUserId()));
        } else {
            return ResVo.fail(StatusEnum.LOGIN_FAILED_MIXED, "登录失败，请重试");
        }
    }

    /**
     * 判断是否有登录
     *
     * @return
     */
    @RequestMapping(path = "isLogined")
    public ResVo<Boolean> isLogined() {
        return ResVo.ok(ReqInfoContext.getReqInfo().getUserId() != null);
    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("info")
    public ResVo<BaseUserInfoDTO> info() {
        BaseUserInfoDTO user = ReqInfoContext.getReqInfo().getUser();
        return ResVo.ok(user);
    }

    /**
     * 登出
     *
     * @param response
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping("logout")
    public ResVo<Boolean> logOut(HttpServletResponse response) {
        Optional.ofNullable(ReqInfoContext.getReqInfo()).ifPresent(s -> loginOutService.logout(s.getSession()));
        // 为什么不后端实现重定向？ 重定向交给前端执行，避免由于前后端分离，本地开发时端口不一致导致的问题
        // response.sendRedirect("/");

        // 移除cookie
        SessionUtil.delCookies(LoginService.SESSION_KEY);
        return ResVo.ok(true);
    }
}
