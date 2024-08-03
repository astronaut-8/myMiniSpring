package org.springframework.test.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */

public class Car {
    @Value("${brand}")
    private String brand;

    private int price;
    private LocalDate produceDate;
    public Car() {
    }

    public Car(String brand) {
        this.brand = brand;
    }

    public Car(String brand, int price, LocalDate produceDate) {
        this.brand = brand;
        this.price = price;
        this.produceDate = produceDate;
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




    /**
     * 获取
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * 获取
     * @return produceDate
     */
    public LocalDate getProduceDate() {
        return produceDate;
    }

    /**
     * 设置
     * @param produceDate
     */
    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    public String toString() {
        return "Car{brand = " + brand + ", price = " + price + ", produceDate = " + produceDate + "}";
    }
}
