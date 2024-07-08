package org.springframework.beans.factory.support;

import org.springframework.beans.factory.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/8
 * {@code @msg} reserved
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    //根据bean的无参构造实例化 简单实现
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor constructor = beanClass.getConstructor();
            return constructor.newInstance();
            //为什么这里使用获取class的构造器后再newInstance
            //因为直接class的新实例对象，只能使用class的公有无参构造器，使用constructor的new，可以有参无参，并且使用setAccessible，可以绕过访问控制，访问私有构造方法
        }catch (Exception e){
            throw new BeansException("Failed to instantiate " + beanClass.getName(),e);
        }
    }

}
