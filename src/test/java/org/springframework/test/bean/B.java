package org.springframework.test.bean;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/4
 * {@code @msg} reserved
 */
public class B {
    private A a;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
