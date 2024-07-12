package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/12
 * {@code @msg} reserved
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean,String beanName) throws BeansException;

    Object applyBeanPostProcessorAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
