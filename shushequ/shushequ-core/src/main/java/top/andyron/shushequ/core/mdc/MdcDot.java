package top.andyron.shushequ.core.mdc;

import java.lang.annotation.*;

/**
  * @author andyron
 * @date 2026/4/17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MdcDot {
    String bizCode() default "";
}
