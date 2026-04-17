package top.andyron.shushequ.service.rank.service.listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.andyron.shushequ.api.model.context.ReqInfoContext;
import top.andyron.shushequ.api.model.enums.ArticleEventEnum;
import top.andyron.shushequ.api.model.event.ArticleMsgEvent;
import top.andyron.shushequ.api.model.vo.notify.NotifyMsgEvent;
import top.andyron.shushequ.service.article.repository.entity.ArticleDO;
import top.andyron.shushequ.service.comment.repository.entity.CommentDO;
import top.andyron.shushequ.service.rank.service.UserActivityRankService;
import top.andyron.shushequ.service.rank.service.model.ActivityScoreBo;
import top.andyron.shushequ.service.user.repository.entity.UserFootDO;
import top.andyron.shushequ.service.user.repository.entity.UserRelationDO;

/**
 * 用户活跃相关的消息监听器
 *
 * @author andyron
 * @date 2026/4/17
 */
@Component
public class UserActivityListener {
    @Autowired
    private UserActivityRankService userActivityRankService;

    /**
     * 用户操作行为，增加对应的积分
     *
     * @param msgEvent
     */
    @EventListener(classes = NotifyMsgEvent.class)
    @Async
    public void notifyMsgListener(NotifyMsgEvent msgEvent) {
        switch (msgEvent.getNotifyType()) {
            case COMMENT:
            case REPLY:
                CommentDO comment = (CommentDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setRate(true).setArticleId(comment.getArticleId()));
                break;
            case COLLECT:
                UserFootDO foot = (UserFootDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setCollect(true).setArticleId(foot.getDocumentId()));
                break;
            case CANCEL_COLLECT:
                foot = (UserFootDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setCollect(false).setArticleId(foot.getDocumentId()));
                break;
            case PRAISE:
                foot = (UserFootDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setPraise(true).setArticleId(foot.getDocumentId()));
                break;
            case CANCEL_PRAISE:
                foot = (UserFootDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setPraise(false).setArticleId(foot.getDocumentId()));
                break;
            case FOLLOW:
                UserRelationDO relation = (UserRelationDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setFollow(true).setFollowedUserId(relation.getUserId()));
                break;
            case CANCEL_FOLLOW:
                relation = (UserRelationDO) msgEvent.getContent();
                userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setFollow(false).setFollowedUserId(relation.getUserId()));
                break;
            default:
        }
    }

    /**
     * 发布文章，更新对应的积分
     *
     * @param event
     */
    @Async
    @EventListener(ArticleMsgEvent.class)
    public void publishArticleListener(ArticleMsgEvent<ArticleDO> event) {
        ArticleEventEnum type = event.getType();
        if (type == ArticleEventEnum.ONLINE) {
            userActivityRankService.addActivityScore(ReqInfoContext.getReqInfo().getUserId(), new ActivityScoreBo().setPublishArticle(true).setArticleId(event.getContent().getId()));
        }
    }

}
