package org.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.factory.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/8
 * {@code @msg} reserved
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback((MethodInterceptor)(obj,method,argsTemp,proxy) -> proxy.invokeSuper(obj,argsTemp));
        return enhancer.create();
    }
}
