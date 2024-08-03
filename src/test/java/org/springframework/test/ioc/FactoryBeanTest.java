package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.test.bean.Car;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car.getBrand());
    }
}
