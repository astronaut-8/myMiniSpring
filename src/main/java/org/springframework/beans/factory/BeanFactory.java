package org.springframework.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} 定义存放bean的容器beanFactory，只有注册和获取两个方法
 */
public class BeanFactory {
    //TODO  这个map可以是final吗？
    private Map<String,Object> beanMap = new HashMap<>();

    public void registerBean(String name,Object bean){
        beanMap.put(name,bean);
    }

    public Object getBean(String name){
        return beanMap.get(name);
    }
}
