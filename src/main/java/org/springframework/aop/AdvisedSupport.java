package org.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} 管理和操作通知者（Advisor）列表
 */
public class AdvisedSupport {
    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;//方法拦截器
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
