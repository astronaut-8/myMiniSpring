package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/5
 * {@code @msg} reserved
 */
public interface ObjectFactory <T>{
    T getObject() throws BeansException;
}
