package org.springframework.test.common;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.test.bean.Car;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
