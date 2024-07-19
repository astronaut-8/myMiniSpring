package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.Person;
import org.springframework.context.suport.ClassPathXmlApplicationContext;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/14
 * {@code @msg} reserved
 */
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person",Person.class);
        System.out.println(person);

        Car car = applicationContext.getBean("car",Car.class);
        System.out.println(car);
    }
}
