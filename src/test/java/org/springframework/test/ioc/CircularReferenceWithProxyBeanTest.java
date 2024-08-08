package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/4
 * {@code @msg} reserved
 */
public class CircularReferenceWithProxyBeanTest {

    @Test
    public void testCircularReference() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        System.out.println(b.getName());
    }
}
