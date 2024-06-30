package org.springframework.beans.factory;


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
}
