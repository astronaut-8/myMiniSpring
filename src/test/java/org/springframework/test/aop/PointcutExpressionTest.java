package org.springframework.test.aop;

import org.junit.Test;
import org.springframework.aop.aspectJ.AspectJExpressionPointcut;
import org.springframework.test.service.HelloService;

import java.lang.reflect.Method;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws Exception{
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method,clazz));
    }
}
