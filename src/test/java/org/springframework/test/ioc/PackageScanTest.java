package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.suport.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/26
 * {@code @msg} reserved
 */
public class PackageScanTest {
    @Test
    public void testScanPackage() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

        Car car = applicationContext.getBean("car",Car.class);
        System.out.println(car);

    }
}
