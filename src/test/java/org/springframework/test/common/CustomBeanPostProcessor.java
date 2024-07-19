package org.springframework.test.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.test.bean.Car;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/12
 * {@code @msg} reserved
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessorBeforeInitialization  " + beanName );
        if ("car".equals(beanName)){
            ((Car) bean).setBrand("mend");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessorAfterInitialization  " + beanName);
        return bean;
    }
}
