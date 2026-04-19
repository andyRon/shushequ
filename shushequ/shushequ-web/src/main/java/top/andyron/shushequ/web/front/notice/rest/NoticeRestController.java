package top.andyron.shushequ.web.front.notice.rest;

import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.NotifyTypeEnum;
import top.andyron.shushequ.api.model.exception.ExceptionUtil;
import top.andyron.shushequ.api.model.vo.NextPageHtmlVo;
import top.andyron.shushequ.api.model.vo.PageListVo;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.api.model.vo.notify.dto.NotifyMsgDTO;
import top.andyron.shushequ.api.model.vo.user.dto.BaseUserInfoDTO;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.core.ws.WebSocketResponseUtil;
import top.andyron.shushequ.service.notify.service.NotifyService;
import top.andyron.shushequ.web.component.TemplateEngineHelper;
import top.andyron.shushequ.web.front.notice.vo.NoticeResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 消息通知
 *
 * @author andyron
 * @date 2026/4/19
 **/
@Permission(role = UserRole.LOGIN)
@RestController
@RequestMapping(path = "notice/api")
public class NoticeRestController {
    @Autowired
    private TemplateEngineHelper templateEngineHelper;

    private NotifyService notifyService;

    public NoticeRestController(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    private PageListVo<NotifyMsgDTO> listItems(String type, Long page, Long pageSize) {
        NotifyTypeEnum typeEnum = NotifyTypeEnum.typeOf(type);
        if (typeEnum == null) {
            throw ExceptionUtil.of(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "type" + type + "非法");
        }
        if (pageSize == null) {
            pageSize = PageParam.DEFAULT_PAGE_SIZE;
        }
        return notifyService.queryUserNotices(ReqInfoContext.getReqInfo().getUserId(),
                typeEnum, PageParam.newPageInstance(page, pageSize));

    }

    /**
     * 消息通知列表，用于前后端分离的场景
     *
     * @param type     @link NotifyTypeEnum
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "list")
    public ResVo<PageListVo<NotifyMsgDTO>> list(@RequestParam(name = "type") String type,
                                                @RequestParam("page") Long page,
                                                @RequestParam(name = "pageSize", required = false) Long pageSize) {
        return ResVo.ok(listItems(type, page, pageSize));
    }

    /**
     * 返回渲染好的分页信息
     *
     * @param type
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "items")
    public ResVo<NextPageHtmlVo> listForView(@RequestParam(name = "type") String type,
                                             @RequestParam("page") Long page,
                                             @RequestParam(name = "pageSize", required = false) Long pageSize) {
        type = type.toLowerCase().trim();
        PageListVo<NotifyMsgDTO> list = listItems(type, page, pageSize);
        NoticeResVo vo = new NoticeResVo();
        vo.setList(list);
        vo.setSelectType(type);
        String html = templateEngineHelper.render("views/notice/tab/notify-" + type, vo);
        return ResVo.ok(new NextPageHtmlVo(html, list.getHasMore()));
    }


    /**
     * 消息通知的检测
     *
     * @param content 发送的内容
     */
    @MessageMapping("/msg/health")
    public void health(String content, SimpMessageHeaderAccessor headerAccessor) {
        ReqInfoContext.ReqInfo user = (ReqInfoContext.ReqInfo) headerAccessor.getUser();
        if (user != null) {
            String response = "ping".equalsIgnoreCase(content) ? "pong" : content;
            notifyService.notifyToUser(user.getUserId(), response);
        }
    }

    /**
     * 给自己发送通知消息 -- 用于测试消息通知
     *
     * @param content
     * @return
     */
    @RequestMapping(path = "notifyToSelf")
    public ResVo<Boolean> notifyToSelf(String content) {
        notifyService.notifyToUser(ReqInfoContext.getReqInfo().getUserId(), content);
        return ResVo.ok(true);
    }

    /**
     * 发送广播消息
     *
     * @param content
     * @return
     */
    @RequestMapping(path = "notifyToAll")
    public ResVo<Boolean> notifyToAll(String content) {
        BaseUserInfoDTO user = ReqInfoContext.getReqInfo().getUser();
        WebSocketResponseUtil.broadcastMsg(NotifyService.NOTIFY_TOPIC, String.format("【%s】发送了一条广播消息: %s", user.getUserName(), content));
        return ResVo.ok(true);
    }
}
