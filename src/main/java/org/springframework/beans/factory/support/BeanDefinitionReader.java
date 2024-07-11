package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/11
 * {@code @msg} 读取beanDefinition的接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(String[] locations) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
}
