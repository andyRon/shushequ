package top.andyron.shushequ.api.model.enums;

import lombok.Getter;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Getter
public enum NotifyStatEnum {
    UNREAD(0, "未读"),
    READ(1, "已读");


    private int stat;
    private String msg;

    NotifyStatEnum(int type, String msg) {
        this.stat = type;
        this.msg = msg;
    }
}
