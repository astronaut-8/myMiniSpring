package org.springframework.beans.factory.support;

import org.springframework.beans.factory.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/8
 * {@code @msg} bean的实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
