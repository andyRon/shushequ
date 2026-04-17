package top.andyron.shushequ.service.notify.help;

import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.enums.NotifyTypeEnum;
import top.andyron.shushequ.api.model.vo.notify.NotifyMsgEvent;
import top.andyron.shushequ.core.util.SpringUtil;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class MsgNotifyHelper {

    /**
     * 消息广播通知
     *
     * @param type    消息类型
     * @param content 消息内容
     * @param <T>     消息类型
     */
    public <T> void publishMsg(NotifyTypeEnum type, T content) {
        SpringUtil.publishEvent(new NotifyMsgEvent<>(this, type, content));
    }


    /**
     * 静态方法使用方式，简化调用方使用
     *
     * @param type 消息类型
     *             * @param content 消息内容
     *             * @param <T>     消息类型
     */
    public static <T> void publish(NotifyTypeEnum type, T content) {
        SpringUtil.getBean(MsgNotifyHelper.class).publishMsg(type, content);
    }
}
