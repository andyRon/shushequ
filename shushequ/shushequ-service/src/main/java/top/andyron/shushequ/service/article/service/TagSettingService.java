package top.andyron.shushequ.service.article.service;


import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.article.SearchTagReq;
import top.andyron.shushequ.api.model.vo.article.TagReq;
import top.andyron.shushequ.api.model.vo.article.dto.TagDTO;

/**
 * 标签后台接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface TagSettingService {

    void saveTag(TagReq tagReq);

    void deleteTag(Integer tagId);

    void operateTag(Integer tagId, Integer pushStatus);

    /**
     * 获取tag列表
     *
     * @param pageParam
     * @return
     */
    PageVo<TagDTO> getTagList(SearchTagReq req);

    TagDTO getTagById(Long tagId);
}
