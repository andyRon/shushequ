package top.andyron.shushequ.api.model.enums.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活跃排行榜时间周期
 *
 * @author andyron
 * @date 2026/4/17
 */
@AllArgsConstructor
@Getter
public enum ActivityRankTimeEnum {
    DAY(1, "day"),
    MONTH(2, "month"),
    ;

    private int type;
    private String desc;

    public static ActivityRankTimeEnum nameOf(String name) {
        if (DAY.desc.equalsIgnoreCase(name)) {
            return DAY;
        } else if (MONTH.desc.equalsIgnoreCase(name)) {
            return MONTH;
        }
        return null;
    }
}
