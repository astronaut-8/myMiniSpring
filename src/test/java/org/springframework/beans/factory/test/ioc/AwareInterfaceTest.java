package org.springframework.beans.factory.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.test.ioc.service.HelloService;
import org.springframework.context.suport.ClassPathXmlApplicationContext;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/17
 * {@code @msg} reserved
 */
public class AwareInterfaceTest {
    @Test
    public void test() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        System.out.println(helloService.getApplicationContext());
        System.out.println(helloService.getBeanFactory());
    }
}
