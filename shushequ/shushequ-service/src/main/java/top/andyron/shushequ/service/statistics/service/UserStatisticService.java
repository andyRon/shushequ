package top.andyron.shushequ.service.statistics.service;

/**
 * 用户统计服务
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface UserStatisticService {
    /**
     * 添加在线人数
     *
     * @param add 正数，表示添加在线人数；负数，表示减少在线人数
     * @return
     */
    int incrOnlineUserCnt(int add);

    /**
     * 查询在线用户人数
     *
     * @return
     */
    int getOnlineUserCnt();

}
