package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/25
 * {@code @msg} reserved
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass , String beanName) throws BeansException;
    //在bean实例化之后，设置属性之前执行
    PropertyValues postProcessPropertyValues(PropertyValues pvs , Object bean , String beanName)
            throws BeansException;
}
