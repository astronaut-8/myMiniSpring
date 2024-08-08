package org.springframework.test.bean;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/4
 * {@code @msg} reserved
 */
public class A {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public void fun(){
        System.out.println("this is a function");
    }
}
