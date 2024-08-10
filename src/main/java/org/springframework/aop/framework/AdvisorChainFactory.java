package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/9
 * {@code @msg} reserved
 */
public interface AdvisorChainFactory {
    List<Object> getInterceptorAndDynamicInterceptionAdvice(AdvisedSupport config , Method method , Class<?> targetClass);
}
