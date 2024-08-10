package org.springframework.test.aop;

import org.junit.Test;
import org.springframework.aop.MethodAnotherBeforeAdvice;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectJ.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adaptor.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adaptor.MethodBeforeAdviceInterceptor;
import org.springframework.test.bean.A;
import org.springframework.test.common.WorldServiceAfterReturnAdvice;
import org.springframework.test.common.WorldServiceAnotherBeforeAdvice;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/10
 * {@code @msg} reserved
 */
public class ProxyFactoryTest {

    @Test
    public void testAdvisor() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        String expression = "execution(* org.springframework.test.service.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceAnotherBeforeAdvice());
        advisor.setAdvice(methodInterceptor);

        AspectJExpressionPointcutAdvisor advisor1 = new AspectJExpressionPointcutAdvisor();
        advisor1.setExpression(expression);
        AfterReturningAdviceInterceptor afterReturningAdviceInterceptor = new AfterReturningAdviceInterceptor(new WorldServiceAfterReturnAdvice());
        advisor1.setAdvice(afterReturningAdviceInterceptor);

        ProxyFactory factory = new ProxyFactory();
        TargetSource targetSource = new TargetSource(worldService);
        factory.setTargetSource(targetSource);
        factory.setProxyTargetClass(true);
        factory.addAdvisor(advisor);
        factory.addAdvisor(advisor1);
        WorldService proxy = (WorldService) factory.getProxy();
        proxy.explode();
    }
}
