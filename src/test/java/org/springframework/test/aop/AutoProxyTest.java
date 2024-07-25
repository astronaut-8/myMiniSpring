package org.springframework.test.aop;

import org.junit.Test;
import org.springframework.context.suport.ClassPathXmlApplicationContext;
import org.springframework.test.service.WorldService;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/25
 * {@code @msg} reserved
 */
public class AutoProxyTest {
    @Test
    public void testAutoProxy(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }


}
