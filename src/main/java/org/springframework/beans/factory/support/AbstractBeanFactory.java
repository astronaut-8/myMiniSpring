package org.springframework.beans.factory.support;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private final Map<String,Object> factoryBeanObjectCache = new HashMap<>();
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();
    @Override
    public Object getBean(String name) throws BeansException {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null){
            return getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name,beanDefinition);
        return getObjectForBeanInstance(bean,name);
    }
    protected Object getObjectForBeanInstance(Object beanInstance,String beanName){
        Object object = beanInstance;
        if (beanInstance instanceof FactoryBean){
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try{
                if (factoryBean.isSingleton()){
                    //singleton作用域bean从缓存中获取
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (object == null){
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName,object);
                    }
                }else{
                    //prototype - 新创建bean
                    object = factoryBean.getObject();
                }
            }catch (Exception e){
                throw new BeansException("FactoryBean threw exception on object " + beanName,e);
            }
        }
        return object;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public <T> T getBean(String name, Class<T> requireType) throws BeansException {
        return ((T) getBean(name));
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }
    public void addEmbeddedValueResolver(StringValueResolver valueResolver){
        this.embeddedValueResolvers.add(valueResolver);
    }
    public String resolveEmbeddedValue(String value){
        String res = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers){
            res = resolver.resolveStringValue(res);
        }
        return res;
    }
}
