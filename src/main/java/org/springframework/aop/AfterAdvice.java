package org.springframework.aop;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public interface AfterAdvice extends Advice {
    void after(Method method, Object[] args, Object target) throws Throwable;
}
