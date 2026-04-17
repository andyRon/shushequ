package top.andyron.shushequ.api.model.enums;

import lombok.Getter;

/**
 * 加精状态枚举
 *
 * @author andyron
 * @date 2026/4/17
 */
@Getter
public enum CreamStatEnum {

    NOT_CREAM(0, "不加精"),
    CREAM(1, "加精");

    CreamStatEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;
    private final String desc;

    public static CreamStatEnum formCode(Integer code) {
        for (CreamStatEnum value : CreamStatEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return CreamStatEnum.NOT_CREAM;
    }
}
