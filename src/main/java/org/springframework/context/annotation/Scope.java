package org.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/26
 * {@code @msg} reserved
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
