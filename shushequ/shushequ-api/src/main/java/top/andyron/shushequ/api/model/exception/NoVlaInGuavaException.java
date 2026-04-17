package top.andyron.shushequ.api.model.exception;

/**
 * 未命中异常
 *
 * @author andyron
 * @date 2026/4/17
 */
public class NoVlaInGuavaException extends RuntimeException {
    public NoVlaInGuavaException(String msg) {
        super(msg);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}