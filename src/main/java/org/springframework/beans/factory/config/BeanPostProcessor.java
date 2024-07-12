package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/12
 * {@code @msg} bean实例化后的修改拓展点
 */
public interface BeanPostProcessor {
    //bean执行初始化方法之前
    Object postProcessorBeforeInitialization(Object bean,String beanName) throws BeansException;

    //bean执行初始化方法之后
    Object postProcessorAfterInitialization(Object bean,String beanName) throws BeansException;
}
