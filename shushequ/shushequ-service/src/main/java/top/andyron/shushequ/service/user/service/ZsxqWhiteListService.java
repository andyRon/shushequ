package top.andyron.shushequ.service.user.service;

import top.andyron.shushequ.api.model.enums.user.UserAIStatEnum;
import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.user.SearchZsxqUserReq;
import top.andyron.shushequ.api.model.vo.user.ZsxqUserPostReq;
import top.andyron.shushequ.api.model.vo.user.dto.ZsxqUserInfoDTO;

import java.util.List;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ZsxqWhiteListService {
    PageVo<ZsxqUserInfoDTO> getList(SearchZsxqUserReq req);

    void operate(Long id, UserAIStatEnum operate);

    void update(ZsxqUserPostReq req);

    void batchOperate(List<Long> ids, UserAIStatEnum operate);

    void reset(Integer authorId);
}
