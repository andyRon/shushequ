package top.andyron.shushequ.service.user.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import top.andyron.shushequ.api.model.enums.DocumentTypeEnum;
import top.andyron.shushequ.api.model.enums.PraiseStatEnum;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.user.dto.ArticleFootCountDTO;
import top.andyron.shushequ.api.model.vo.user.dto.SimpleUserInfoDTO;
import top.andyron.shushequ.api.model.vo.user.dto.UserFootStatisticDTO;
import top.andyron.shushequ.service.user.repository.entity.UserFootDO;
import top.andyron.shushequ.service.user.repository.mapper.UserFootMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Repository
public class UserFootDao extends ServiceImpl<UserFootMapper, UserFootDO> {
    public UserFootDO getByDocumentAndUserId(Long documentId, Integer type, Long userId) {
        LambdaQueryWrapper<UserFootDO> query = Wrappers.lambdaQuery();
        query.eq(UserFootDO::getDocumentId, documentId)
                .eq(UserFootDO::getDocumentType, type)
                .eq(UserFootDO::getUserId, userId);
        return baseMapper.selectOne(query);
    }

    public List<SimpleUserInfoDTO> listDocumentPraisedUsers(Long documentId, Integer type, int size) {
        return baseMapper.listSimpleUserInfosByArticleId(documentId, type, size);
    }

    /**
     * 查询用户收藏的文章列表
     *
     * @param userId
     * @param pageParam
     * @return
     */
    public List<Long> listCollectedArticlesByUserId(Long userId, PageParam pageParam) {
        return baseMapper.listCollectedArticlesByUserId(userId, pageParam);
    }


    /**
     * 查询用户阅读的文章列表
     *
     * @param userId
     * @param pageParam
     * @return
     */
    public List<Long> listReadArticleByUserId(Long userId, PageParam pageParam) {
        return baseMapper.listReadArticleByUserId(userId, pageParam);
    }

    /**
     * 查询文章计数信息
     *
     * @param articleId
     * @return
     */
    public ArticleFootCountDTO countArticleByArticleId(Long articleId) {
        return baseMapper.countArticleByArticleId(articleId);
    }

    /**
     * 查询作者的文章统计
     *
     * @param author
     * @return
     */
    public ArticleFootCountDTO countArticleByUserId(Long author) {
        // 统计收藏、点赞数
        ArticleFootCountDTO count = baseMapper.countArticleByUserId(author);
        Optional.ofNullable(count).ifPresent(s -> s.setReadCount(baseMapper.countArticleReadsByUserId(author)));
        return count;
    }

    /**
     * 查询评论的点赞数
     *
     * @param commentId
     * @return
     */
    public Long countCommentPraise(Long commentId) {
        return lambdaQuery()
                .eq(UserFootDO::getDocumentId, commentId)
                .eq(UserFootDO::getDocumentType, DocumentTypeEnum.COMMENT.getCode())
                .eq(UserFootDO::getPraiseStat, PraiseStatEnum.PRAISE.getCode())
                .count();
    }

    public UserFootStatisticDTO getFootCount() {
        return baseMapper.getFootCount();

    }
}
