package org.springframework.context.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicasterEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners){
            if (supportsEvent(applicationListener,event)){
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    //监听器是否对该事件感兴趣
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Type type = applicationListener.getClass().getGenericInterfaces()[0]; //获取第一个泛型接口的类型信息
        //ParameterizedType表示带有泛型参数的类型
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];//第一个实际类型参数
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        }catch (ClassNotFoundException e){
            throw new BeansException("wrong event class name : " + className );
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
