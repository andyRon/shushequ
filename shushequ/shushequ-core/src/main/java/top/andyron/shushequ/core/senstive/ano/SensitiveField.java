package top.andyron.shushequ.core.senstive.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
  * @author andyron
 * @date 2026/4/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SensitiveField {
    /**
     * 绑定的db中的哪个字段
     *
     * @return
     */
    String bind() default "";

}
