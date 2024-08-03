package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/3
 * {@code @msg} reserved
 */
public class TypeConversionSecondPartTest {
    @Test
    public void testConversionService() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car.toString());
    }
}
