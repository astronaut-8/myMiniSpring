package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.*;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry
, ConfigurableListableBeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null){
            throw new BeansException("no bean named " + beanName + " id defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }



    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException {
        Map<String,T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)){ //判断一个类是否可以赋值给另一个类 a.b a是b的接口或者父类
                T bean = (T) getBean(beanName);
                result.put(beanName,bean);
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[0]);
    }

    @Override
    public void preInstantiateSingleton() throws BeansException {
        beanDefinitionMap.forEach((beanName,beanDefinition) -> {
            if (beanDefinition.isSingleton() && !beanDefinition.isLazyInit()){
                getBean(beanName);
            }
        });
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String,BeanDefinition> entry : beanDefinitionMap.entrySet()){
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)){
                beanNames.add(entry.getKey());
            }
        }
        if (beanNames.size() == 1){
            return getBean(beanNames.get(0),requiredType);
        }

        throw new BeansException(requiredType + " expected single bean but found " + beanNames.size() + "beanNames");
    }
}
