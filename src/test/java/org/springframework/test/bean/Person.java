package org.springframework.test.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */
@Component
public class Person implements InitializingBean, DisposableBean {
    private String name;
    private int age;
    @Autowired
    private Car car;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }



    /**
     * 获取
     * @return car
     */
    public Car getCar() {
        return car;
    }

    /**
     * 设置
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    public String toString() {
        return "Person{name = " + name + ", age = " + age + ", car = " + car + "}";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterProperties");
    }
    public void customInitMethod(){
        System.out.println("customInitMethod");
    }
    public void customDestroyMethod(){
        System.out.println("customDestroyMethod");
    }
}
