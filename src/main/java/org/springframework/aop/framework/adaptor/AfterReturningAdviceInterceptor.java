package org.springframework.aop.framework.adaptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/9
 * {@code @msg} reserved
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor , AfterAdvice {
    private AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor() {
    }

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object res = methodInvocation.proceed();
        this.advice.afterReturning(res,methodInvocation.getMethod(),methodInvocation.getArguments(),methodInvocation.getThis());
        return res;
    }
}
