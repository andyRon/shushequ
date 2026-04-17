package top.andyron.shushequ.core.util.id.snowflake;

/**
  * @author andyron
 * @date 2026/4/17
 */
public interface IdGenerator {
    /**
     * 生成分布式id
     *
     * @return
     */
    Long nextId();
}
