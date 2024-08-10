package org.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class GenericInterceptor implements MethodInterceptor {
    private AnotherBeforeAdvice anotherBeforeAdvice;
    private AnotherAfterAdvice anotherAfterAdvice;
    private AnotherAfterReturningAdvice anotherAfterReturningAdvice;
    private ThrowsAdvice throwsAdvice;
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        try{
            if (anotherBeforeAdvice != null){
                anotherBeforeAdvice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
            }
            result = invocation.proceed();
        }catch (Exception throwable){
            if (throwsAdvice != null){
                throwsAdvice.throwsHandle(throwable, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
        }finally {
            if (anotherAfterAdvice != null){
                anotherAfterAdvice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            }
        }
        if (anotherAfterReturningAdvice != null){
            anotherAfterReturningAdvice.afterReturning(result, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        }
        return result;
    }

    public void setBeforeAdvice(AnotherBeforeAdvice anotherBeforeAdvice) {
        this.anotherBeforeAdvice = anotherBeforeAdvice;
    }

    public void setAfterAdvice(AnotherAfterAdvice anotherAfterAdvice) {
        this.anotherAfterAdvice = anotherAfterAdvice;
    }

    public void setAfterReturningAdvice(AnotherAfterReturningAdvice anotherAfterReturningAdvice) {
        this.anotherAfterReturningAdvice = anotherAfterReturningAdvice;
    }

    public void setThrowsAdvice(ThrowsAdvice throwsAdvice) {
        this.throwsAdvice = throwsAdvice;
    }
}
