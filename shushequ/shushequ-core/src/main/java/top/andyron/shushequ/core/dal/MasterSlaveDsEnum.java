package top.andyron.shushequ.core.dal;

/**
 * 主从数据源的枚举
 *
  * @author andyron
 * @date 2026/4/17
 */
public enum MasterSlaveDsEnum implements DS {
    /**
     * master主数据源类型
     */
    MASTER,
    /**
     * slave从数据源类型
     */
    SLAVE;
}
