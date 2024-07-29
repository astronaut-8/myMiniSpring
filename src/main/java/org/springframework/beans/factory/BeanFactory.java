package org.springframework.beans.factory;


import org.springframework.beans.BeansException;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} 定义存放bean的容器beanFactory
 */
public interface BeanFactory {

    /**
     *
     * @param name bean的名称
     * @return 返回bean
     * @throws BeansException bean不存在的时候返回exception
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据名称和类型查找Bean
     * @param name name
     * @param requireType 类型
     * @return bean
     * @param <T> 泛型
     * @throws BeansException 不存在
     */
    <T> T getBean(String name , Class<T> requireType) throws BeansException;
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
