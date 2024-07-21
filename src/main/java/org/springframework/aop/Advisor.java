package org.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/21
 * {@code @msg} reserved
 */
public interface Advisor {
    Advice getAdvice();
}
