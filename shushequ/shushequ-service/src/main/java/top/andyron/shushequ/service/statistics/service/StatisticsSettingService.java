package top.andyron.shushequ.service.statistics.service;

import jakarta.servlet.http.HttpServletResponse;
import top.andyron.shushequ.api.model.vo.statistics.dto.StatisticsCountDTO;
import top.andyron.shushequ.api.model.vo.statistics.dto.StatisticsDayDTO;
import java.util.List;

/**
 * 数据统计后台接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface StatisticsSettingService {

    /**
     * 保存计数
     *
     * @param host
     */
    void saveRequestCount(String host);

    /**
     * 获取总数
     *
     * @return
     */
    StatisticsCountDTO getStatisticsCount();

    /**
     * 获取每天的PV UV统计数据
     *
     * @param day
     * @return
     */
    List<StatisticsDayDTO> getPvUvDayList(Integer day);

    void download2Excel(Integer day, HttpServletResponse response);
}
