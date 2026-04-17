package top.andyron.shushequ.service.article.service.impl;


import org.springframework.stereotype.Service;
import top.andyron.shushequ.service.article.service.TagService;

import java.util.List;

/**
 * 标签Service
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public PageVo<TagDTO> queryTags(String key, PageParam pageParam) {
        List<TagDTO> tagDTOS = tagDao.listOnlineTag(key, pageParam);
        Integer totalCount = tagDao.countOnlineTag(key);
        return PageVo.build(tagDTOS, pageParam.getPageSize(), pageParam.getPageNum(), totalCount);
    }

    @Override
    public Long queryTagId(String tag) {
        return tagDao.selectTagIdByTag(tag);
    }
}
