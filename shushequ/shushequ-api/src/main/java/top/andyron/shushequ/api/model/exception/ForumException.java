package top.andyron.shushequ.api.model.exception;

import lombok.Getter;
import top.andyron.shushequ.api.model.vo.Status;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;

/**
 * 业务异常
 *
 * @author andyron
 * @date 2026/4/17
 */
public class ForumException extends RuntimeException {
    @Getter
    private Status status;

    public ForumException(Status status) {
        this.status = status;
    }

    public ForumException(int code, String msg) {
        this.status = Status.newStatus(code, msg);
    }

    public ForumException(StatusEnum statusEnum, Object... args) {
        this.status = Status.newStatus(statusEnum, args);
    }

}
