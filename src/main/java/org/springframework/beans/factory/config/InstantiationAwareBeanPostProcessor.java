package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/25
 * {@code @msg} reserved
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass , String beanName) throws BeansException;
}
