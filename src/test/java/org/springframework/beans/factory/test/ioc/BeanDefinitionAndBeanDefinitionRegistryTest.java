package org.springframework.beans.factory.test.ioc;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.test.ioc.HelloService;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    public void testGetBean(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("foo","hello"));
        propertyValues.addPropertyValue(new PropertyValue("bar","world"));

        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class,propertyValues);
        beanFactory.registerBeanDefinition("helloService",beanDefinition);
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        System.out.println(helloService.toString());

    }


}
