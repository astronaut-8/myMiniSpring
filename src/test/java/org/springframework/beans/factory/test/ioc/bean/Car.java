package org.springframework.beans.factory.test.ioc.bean;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */
public class Car {
    private String brand;

    public Car() {
    }

    public Car(String brand) {
        this.brand = brand;
    }

    /**
     * 获取
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String toString() {
        return "Car{brand = " + brand + "}";
    }
}
