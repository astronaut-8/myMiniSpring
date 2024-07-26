package org.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/26
 * {@code @msg} reserved
 */
@Target(ElementType.TYPE) // 用于类，接口，枚举
@Retention(RetentionPolicy.RUNTIME)
@Documented //表示使用该注解的元素应该被包含在 Javadoc 文档中
public @interface Component {
    String value() default "";
}
