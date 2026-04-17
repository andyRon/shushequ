package top.andyron.shushequ.core.util.id.snowflake;

import cn.hutool.core.lang.Snowflake;

import java.util.Date;


/**
  * @author andyron
 * @date 2026/4/17
 */
public class HuToolSnowflakeIdGenerator implements IdGenerator {
    private static final Date EPOC = new Date(2023, 1, 1);
    private Snowflake snowflake;

    public HuToolSnowflakeIdGenerator(int workId, int datacenter) {
        snowflake = new Snowflake(EPOC, workId, datacenter, false);
    }

    @Override
    public Long nextId() {
        return snowflake.nextId();
    }
}
