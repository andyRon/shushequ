package top.andyron.shushequ.service.statistics.service;

import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.statistics.dto.StatisticsDayDTO;
import top.andyron.shushequ.service.statistics.repository.entity.RequestCountDO;

import java.util.List;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface RequestCountService {
    RequestCountDO getRequestCount(String host);

    void insert(String host);

    void incrementCount(Long id);

    Long getPvTotalCount();

    List<StatisticsDayDTO> getPvUvDayList(Integer day);

    long count();

    // 分页返回 RequestCountDO
    List<RequestCountDO> listRequestCount(PageParam pageParam);
}
