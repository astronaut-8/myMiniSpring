package org.springframework.test.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.GenericInterceptor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectJ.AspectJExpressionPointcut;
import org.springframework.aop.framework.CglibAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.adaptor.MethodBeforeAdviceInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.test.common.*;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;
import org.springframework.test.service.WorldServiceWithExceptionImpl;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class DynamicProxyTest {
    private AdvisedSupport advisedSupport;

    @Before
    public void setup(){
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService); // 封装代理对象
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }
    @Test
    public void testJdkDynamicProxy() throws Exception{
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception{
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testProxyFactory() throws Exception{
        //jdk
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();

        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }


    @Test
    public void testAllWithException() throws Exception{
        WorldService worldService = new WorldServiceWithExceptionImpl();
        GenericInterceptor genericInterceptor = new GenericInterceptor();
        genericInterceptor.setBeforeAdvice(new WorldServiceBeforeAdvice());
        genericInterceptor.setAfterAdvice(new WorldServiceAfterAdvice());
        genericInterceptor.setThrowsAdvice(new WorldServiceThrowsAdvice());
        genericInterceptor.setAfterReturningAdvice(new WorldServiceAfterRunningAdvice());

        advisedSupport.setMethodInterceptor(genericInterceptor);
        advisedSupport.setTargetSource(new TargetSource(worldService));

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }
}
