package org.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class GenericInterceptor implements MethodInterceptor {
    private BeforeAdvice beforeAdvice;
    private AfterAdvice  afterAdvice;
    private AfterReturningAdvice afterReturningAdvice;
    private ThrowsAdvice throwsAdvice;
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        try{
            if (beforeAdvice != null){
                beforeAdvice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
            }
            result = invocation.proceed();
        }catch (Exception throwable){
            if (throwsAdvice != null){
                throwsAdvice.throwsHandle(throwable, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
        }finally {
            if (afterAdvice != null){
                afterAdvice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
        }
        if (afterReturningAdvice != null){
            afterReturningAdvice.afterReturning(result, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        }
        return result;
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }

    public void setAfterReturningAdvice(AfterReturningAdvice afterReturningAdvice) {
        this.afterReturningAdvice = afterReturningAdvice;
    }

    public void setThrowsAdvice(ThrowsAdvice throwsAdvice) {
        this.throwsAdvice = throwsAdvice;
    }
}
