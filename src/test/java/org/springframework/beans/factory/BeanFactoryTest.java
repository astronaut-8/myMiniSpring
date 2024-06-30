package org.springframework.beans.factory;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService",beanDefinition);
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        System.out.println(helloService.sayHello());

    }


}
