package org.springframework.test.common;

import org.springframework.aop.MethodAnotherBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/4
 * {@code @msg} reserved
 */
public class AAnotherBeforeAdvice implements MethodAnotherBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before advice");
    }
}
