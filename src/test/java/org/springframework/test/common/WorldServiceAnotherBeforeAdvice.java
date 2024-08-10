package org.springframework.test.common;

import org.springframework.aop.MethodAnotherBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class WorldServiceAnotherBeforeAdvice implements MethodAnotherBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before advice");
    }
}
