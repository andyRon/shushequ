package top.andyron.shushequ.web.front.article.vo;

import top.andyron.shushequ.api.model.vo.article.dto.ArticleDTO;
import top.andyron.shushequ.api.model.vo.article.dto.ArticleOtherDTO;
import top.andyron.shushequ.api.model.vo.article.dto.ArticlePayInfoDTO;
import top.andyron.shushequ.api.model.vo.comment.dto.TopCommentDTO;
import top.andyron.shushequ.api.model.vo.recommend.SideBarDTO;
import top.andyron.shushequ.api.model.vo.user.dto.SimpleUserInfoDTO;
import top.andyron.shushequ.api.model.vo.user.dto.UserStatisticInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class ArticleDetailVo {
    /**
     * 文章信息
     */
    private ArticleDTO article;

    /**
     * 评论信息
     */
    private List<TopCommentDTO> comments;

    /**
     * 一级评论总数
     */
    private Integer topCommentTotal;

    /**
     * 热门评论
     */
    private TopCommentDTO hotComment;

    /**
     * 划线引用评论
     */
    private List<TopCommentDTO> highlightComments;

    /**
     * 作者相关信息
     */
    private UserStatisticInfoDTO author;


    private ArticlePayInfoDTO payInfo;

    // 其他的信息，比如说翻页，比如说阅读类型
    private ArticleOtherDTO other;

    /**
     * 侧边栏信息
     */
    private List<SideBarDTO> sideBarItems;


    /**
     * 打赏用户列表
     */
    private List<SimpleUserInfoDTO> payUsers;
}
