package org.springframework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class CglibAopProxy implements AopProxy{
    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }


    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private final AdvisedSupport advised;

        private DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            //获取目标对象
            Object target = advised.getTargetSource().getTarget();
            Class<?> targetClass = target.getClass();
            Object retVal = null;
            List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,targetClass);
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(proxy,target,method,args,targetClass,chain,methodProxy);
            if (chain == null || chain.isEmpty()) {
                retVal = methodProxy.invoke(target,args);
            } else {
                retVal = methodInvocation.proceed();
            }
            return retVal;
        }
        private static class CglibMethodInvocation extends ReflectiveMethodInvocation{
            private final MethodProxy methodProxy;

            public CglibMethodInvocation(Object proxy, Object target , Method method, Object[] arguments, Class<?> targetClass , List<Object> interceptorsAndDynamicMethodMatchers , MethodProxy methodProxy) {
                super(proxy , target, method, arguments , targetClass , interceptorsAndDynamicMethodMatchers);
                this.methodProxy = methodProxy;
            }

            @Override
            public Object proceed() throws Throwable {
                return super.proceed();
            }
        }
    }
}
