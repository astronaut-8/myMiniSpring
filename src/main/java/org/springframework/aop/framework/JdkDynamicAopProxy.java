package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class JdkDynamicAopProxy implements AopProxy , InvocationHandler {
    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //判断是否需要代理
        if (advised.getMethodMatcher().matches(method,advised.getTargetSource().getTarget().getClass())){
            //代理方法
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),method,args));
        }
        return method.invoke(advised.getTargetSource().getTarget(),args);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader() , advised.getTargetSource().getTargetClass() , this);
    }
}
