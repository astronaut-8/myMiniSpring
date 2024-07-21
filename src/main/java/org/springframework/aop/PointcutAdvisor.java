package org.springframework.aop;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/21
 * {@code @msg} reserved
 */
public interface PointcutAdvisor extends Advisor{
    Pointcut getPoint();
}
