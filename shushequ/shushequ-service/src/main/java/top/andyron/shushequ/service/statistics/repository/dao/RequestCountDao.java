package top.andyron.shushequ.service.statistics.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import top.andyron.shushequ.api.model.vo.PageParam;
import top.andyron.shushequ.api.model.vo.statistics.dto.StatisticsDayDTO;
import top.andyron.shushequ.service.statistics.repository.entity.RequestCountDO;
import top.andyron.shushequ.service.statistics.repository.mapper.RequestCountMapper;

import java.sql.Date;
import java.util.List;

/**
 * 请求计数
 *
 * @author andyron
 * @date 2026/4/17
 */
@Repository
public class RequestCountDao extends ServiceImpl<RequestCountMapper, RequestCountDO> {

    public Long getPvTotalCount() {
        return baseMapper.getPvTotalCount();
    }

    /**
     * 获取请求数据
     *
     * @param host
     * @param date
     * @return
     */
    public RequestCountDO getRequestCount(String host, Date date) {
        return lambdaQuery()
                .eq(RequestCountDO::getHost, host)
                .eq(RequestCountDO::getDate, date)
                .one();
    }

    public List<RequestCountDO> listRequestCount(PageParam pageParam) {
        LambdaQueryWrapper<RequestCountDO> query = Wrappers.lambdaQuery();
        query.orderByDesc(RequestCountDO::getId);
        if (pageParam != null) {
            query.last(PageParam.getLimitSql(pageParam));
        }
        return baseMapper.selectList(query);
    }

    /**
     * 获取 PV UV 数据列表
     * @param day
     * @return
     */
    public List<StatisticsDayDTO> getPvUvDayList(Integer day) {
        return baseMapper.getPvUvDayList(day);
    }

    public void incrementCount(Long id) {
        baseMapper.incrementCount(id);
    }

    /**
     * 插入或更新请求计数(使用ON DUPLICATE KEY UPDATE避免并发插入问题)
     *
     * @param host 主机地址
     * @param date 日期
     */
    public void insertOrUpdate(String host, Date date) {
        baseMapper.insertOrUpdate(host, date);
    }
}
