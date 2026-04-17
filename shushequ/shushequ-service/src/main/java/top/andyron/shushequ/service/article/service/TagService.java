package top.andyron.shushequ.service.article.service;


import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.article.dto.TagDTO;

/**
 * 标签Service
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface TagService {

    PageVo<TagDTO> queryTags(String key, PageParam pageParam);

    Long queryTagId(String tag);
}
