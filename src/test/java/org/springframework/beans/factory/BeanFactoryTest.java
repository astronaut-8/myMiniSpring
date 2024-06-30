package org.springframework.beans.factory;

import org.junit.Test;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("helloService",new HelloService());
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        System.out.println(helloService.sayHello());

    }

    class HelloService{
        public String sayHello(){
            System.out.println("hello from function ");
            return "hello from return";
        }
    }
}
