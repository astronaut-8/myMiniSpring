package org.springframework.beans.factory.test.ioc;

import org.junit.Test;
import org.springframework.context.suport.ClassPathXmlApplicationContext;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/15
 * {@code @msg} reserved
 */
public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();
    }
}
