package org.springframework.beans.factory.test.ioc;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class HelloService {

    public String sayHello() {
        System.out.println("hello from function");
        return "hello from return";
    }
}
