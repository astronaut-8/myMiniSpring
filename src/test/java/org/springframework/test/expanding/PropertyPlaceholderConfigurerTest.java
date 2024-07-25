package org.springframework.test.expanding;

import org.junit.Test;
import org.springframework.context.suport.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/25
 * {@code @msg} reserved
 */
public class PropertyPlaceholderConfigurerTest {

    @Test
    public void test() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

        Car car = applicationContext.getBean("car",Car.class);
        System.out.println(car.getBrand());
    }
}
