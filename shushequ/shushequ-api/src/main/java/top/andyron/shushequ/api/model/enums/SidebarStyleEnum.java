package top.andyron.shushequ.api.model.enums;

import lombok.Getter;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Getter
public enum SidebarStyleEnum {

    NOTICE(1),
    ARTICLES(2),
    RECOMMEND(3),
    ABOUT(4),
    COLUMN(5),
    PDF(6),
    SUBSCRIBE(7),
    /**
     * 活跃排行榜
     */
    ACTIVITY_RANK(8);

    private int style;

    SidebarStyleEnum(int style) {
        this.style = style;
    }
}
