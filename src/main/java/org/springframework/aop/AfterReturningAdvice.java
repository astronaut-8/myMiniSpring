package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/9
 * {@code @msg} reserved
 */
public interface AfterReturningAdvice extends AfterAdvice{
    void afterReturning(Object returnValue, Method method , Object[] args, Object target) throws Throwable;
}
