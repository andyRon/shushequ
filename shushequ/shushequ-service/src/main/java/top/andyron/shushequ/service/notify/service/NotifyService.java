package top.andyron.shushequ.service.notify.service;

api.model.enums.NotifyTypeEnum;
api.model.vo.PageListVo;
api.model.vo.PageParam;
api.model.vo.notify.dto.NotifyMsgDTO;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import top.andyron.shushequ.api.model.enums.NotifyTypeEnum;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.notify.dto.NotifyMsgDTO;
import top.andyron.shushequ.service.user.repository.entity.UserFootDO;

import java.util.Map;

/**
 * 消息通知服务类
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface NotifyService {
    public static String NOTIFY_TOPIC = "/msg";


    /**
     * 查询用户未读消息数量
     *
     * @param userId
     * @return
     */
    int queryUserNotifyMsgCount(Long userId);

    /**
     * 查询通知列表
     *
     * @param userId
     * @param type
     * @param page
     * @return
     */
    PageListVo<NotifyMsgDTO> queryUserNotices(Long userId, NotifyTypeEnum type, PageParam page);

    /**
     * 查询未读消息数
     *
     * @param userId
     * @return
     */
    Map<String, Integer> queryUnreadCounts(long userId);

    /**
     * 保存通知
     *
     * @param foot
     * @param notifyTypeEnum
     */
    void saveArticleNotify(UserFootDO foot, NotifyTypeEnum notifyTypeEnum);


    // -------------------------------------------- 下面是与用户的websocket长连接维护相关实现 -------------------------

    /**
     * ws: 给用户发送消息通知
     *
     * @param userId 用户id
     * @param msg    通知内容
     */
    void notifyToUser(Long userId, String msg);

    /**
     * ws: 给用户发送消息通知（带类型）
     *
     * @param userId 用户id
     * @param type   通知类型
     * @param msg    通知内容
     */
    void notifyToUser(Long userId, NotifyTypeEnum type, String msg);


    /**
     * ws: 维护与用户的长连接通道
     *
     * @param accessor
     */
    void notifyChannelMaintain(StompHeaderAccessor accessor);
}
