package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/12
 * {@code @msg} "Configurable" 表示该接口提供了对 Bean 工厂配置的访问和修改能力
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingleton() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
