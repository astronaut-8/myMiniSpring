package org.springframework.aop.framework;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} 反射方法调用
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object proxy;
    protected final Object target;
    protected final Method method;
    protected final Object[] arguments;
    protected final Class<?> targetClass;
    protected final List<Object> interceptorsAndDynamicMethodMatchers;
    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        //初始化index为-1 每调用一次proceed index就+1
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1){
            //调用次数 = 拦截器个数
            //触发当前method方法
            return method.invoke(this.target,this.arguments);
        }
        Object o = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        return ((MethodInterceptor) o).invoke(this);//循环调用拦截器
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        //获取静态部分
        return method;
    }
}
