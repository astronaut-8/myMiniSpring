package org.springframework.aop;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
