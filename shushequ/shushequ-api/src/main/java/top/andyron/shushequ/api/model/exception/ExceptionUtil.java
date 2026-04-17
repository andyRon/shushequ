package top.andyron.shushequ.api.model.exception;

import top.andyron.shushequ.api.model.vo.constants.StatusEnum;

/**
 *
 * @author andyron
 * @date 2026/4/17
 */
public class ExceptionUtil {

    public static ForumException of(StatusEnum status, Object... args) {
        return new ForumException(status, args);
    }

}
