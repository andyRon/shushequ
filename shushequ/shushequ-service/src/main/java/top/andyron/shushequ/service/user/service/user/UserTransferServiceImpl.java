package top.andyron.shushequ.service.user.service.user;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.exception.ExceptionUtil;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.core.util.SessionUtil;
import top.andyron.shushequ.service.user.repository.dao.UserAiDao;
import top.andyron.shushequ.service.user.repository.dao.UserDao;
import top.andyron.shushequ.service.user.repository.entity.UserAiDO;
import top.andyron.shushequ.service.user.repository.entity.UserDO;
import top.andyron.shushequ.service.user.service.LoginService;
import top.andyron.shushequ.service.user.service.UserTransferService;
import top.andyron.shushequ.service.user.service.help.UserPwdEncoder;
import top.andyron.shushequ.service.user.service.help.UserSessionHelper;

import java.util.Objects;

/**
 * 基于验证码、用户名密码的登录方式
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
@Slf4j
public class UserTransferServiceImpl implements UserTransferService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAiDao userAiDao;

    @Autowired
    private UserSessionHelper userSessionHelper;

    @Autowired
    private UserPwdEncoder userPwdEncoder;


    @Override
    public boolean transferUser(String uname, String pwd) {
        UserDO user = userDao.getUserByUserName(uname);
        if (user == null) {
            throw ExceptionUtil.of(StatusEnum.USER_NOT_EXISTS, "userName=" + uname);
        }

        if (!userPwdEncoder.match(pwd, user.getPassword())) {
            throw ExceptionUtil.of(StatusEnum.USER_PWD_ERROR);
        }

        // 将当前登录用户，与目标用户进行置换
        return transferUser(user);
    }

    @Override
    public boolean transferUser(String starNumber) {
        // 根据星球号找到原始用户
        UserAiDO userAiDO = userAiDao.getByStarNumber(starNumber);
        if (userAiDO == null) {
            throw ExceptionUtil.of(StatusEnum.USER_NOT_EXISTS, "starNumber=" + starNumber);
        }

        // 找到需要迁移到的目标用户
        UserDO targetUser = userDao.getUserByUserId(userAiDO.getUserId());
        return transferUser(targetUser);
    }

    /**
     * 账号迁移
     *
     * @param targetUser
     */
    private boolean transferUser(UserDO targetUser) {
        Long currentUserId = ReqInfoContext.getReqInfo().getUserId();
        if (Objects.equals(currentUserId, targetUser.getId())) {
            // 同一个用户，无需迁移
            throw ExceptionUtil.of(StatusEnum.UNEXPECT_ERROR, "当前用户与目标用户相同，无需迁移");
        }

        UserDO loginUser = userDao.getUserByUserId(currentUserId);
        if (StringUtils.isBlank(loginUser.getThirdAccountId())) {
            throw ExceptionUtil.of(StatusEnum.UNEXPECT_ERROR, "非企业号登录，无需账号迁移");
        }

        String oldId = targetUser.getThirdAccountId();
        String transId = loginUser.getThirdAccountId();

        // 将当前登录用户的微信身份信息，转移到目标用户
        targetUser.setThirdAccountId(transId);
        userDao.updateUser(targetUser);

        // 将目标用户的微信身份信息，转移到当前登录用户
        loginUser.setThirdAccountId(oldId);
        userDao.updateUser(loginUser);

        // 迁移成功，重置上下文信息
        ReqInfoContext.getReqInfo().setUserId(targetUser.getId());
        String session = userSessionHelper.genSession(targetUser.getId());
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        if (response != null) {
            response.addCookie(SessionUtil.newCookie(LoginService.SESSION_KEY, session));
        }
        log.info("用户迁移成功，从 {} -> {}", loginUser.getId(), targetUser.getId());
        return true;
    }
}
