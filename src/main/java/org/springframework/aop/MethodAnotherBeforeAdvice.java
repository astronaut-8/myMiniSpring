package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public interface MethodAnotherBeforeAdvice extends AnotherBeforeAdvice {
    void before(Method method,Object[] args,Object target) throws Throwable;
}
