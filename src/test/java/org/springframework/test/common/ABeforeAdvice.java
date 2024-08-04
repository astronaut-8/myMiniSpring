package org.springframework.test.common;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/4
 * {@code @msg} reserved
 */
public class ABeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before advice");
    }
}
