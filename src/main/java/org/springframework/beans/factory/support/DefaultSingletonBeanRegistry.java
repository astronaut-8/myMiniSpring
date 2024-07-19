package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonObjects = new HashMap<>();
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }
    public void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }
    public void destroySingletons(){
        ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet()) ;

        beanNames.forEach(beanName ->{
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try{
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name " + beanName + "threw an exception",e);
            }
        });
    }
}
