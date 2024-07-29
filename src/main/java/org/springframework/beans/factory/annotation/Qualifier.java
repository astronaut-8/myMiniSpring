package org.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} reserved
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
