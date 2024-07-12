package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/12
 * {@code @msg} "Configurable" 表示该接口提供了对 Bean 工厂配置的访问和修改能力
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory{
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     * @throws BeansException 出现了意外。。。
     */
    void preInstantiateSingletons() throws BeansException;
}
