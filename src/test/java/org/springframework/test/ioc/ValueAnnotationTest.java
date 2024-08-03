package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/28
 * {@code @msg} reserved
 */
public class ValueAnnotationTest {
    @Test
    public void testValueAnnotation() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");

        Car car = applicationContext.getBean("car",Car.class);
        System.out.println(car.getBrand());
    }
}
