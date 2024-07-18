package org.springframework.beans.factory.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.test.ioc.bean.Car;
import org.springframework.context.suport.ClassPathXmlApplicationContext;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public class PrototypeBeanTest {

    @Test
    public void testPrototype() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = applicationContext.getBean("car",Car.class);
        Car car2 = applicationContext.getBean("car",Car.class);
        System.out.println(car1 == car2);
    }
}
