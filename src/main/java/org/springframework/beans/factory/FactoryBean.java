package org.springframework.beans.factory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public interface FactoryBean<T>{
    T getObject() throws Exception;
    boolean isSingleton();
}
