package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public interface MethodMatcher {
    boolean matches(Method method,Class<?> targetClass);
}
