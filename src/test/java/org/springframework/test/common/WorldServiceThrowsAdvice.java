package org.springframework.test.common;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class WorldServiceThrowsAdvice implements ThrowsAdvice {
    @Override
    public void throwsHandle(Throwable throwable, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("throws");
    }
}
