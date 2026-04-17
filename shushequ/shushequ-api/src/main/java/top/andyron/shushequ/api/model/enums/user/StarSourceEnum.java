package top.andyron.shushequ.api.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 星球来源枚举
 *
 * @author andyron
 * @date 2026/4/17
 */
@Getter
@AllArgsConstructor
public enum StarSourceEnum {
    /**
     * java进阶
     */
    JAVA_GUIDE(1),
    /**
     * 技术派
     */
    TECH_PAI(2),
    ;
    private int source;
}
