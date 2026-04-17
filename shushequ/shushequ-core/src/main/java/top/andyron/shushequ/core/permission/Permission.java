package top.andyron.shushequ.core.permission;

import java.lang.annotation.*;

/**
  * @author andyron
 * @date 2026/4/17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    /**
     * 限定权限
     *
     * @return
     */
    UserRole role() default UserRole.ALL;
}
