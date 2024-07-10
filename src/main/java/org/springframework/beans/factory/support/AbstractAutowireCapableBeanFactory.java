package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName,beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {

        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition);
            //为bean填充信息
            applyPropertyValues(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try{
            Class beanClass = beanDefinition.getBeanClass();

            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

//                //通过属性的set方法设置属性
//                Class<?> type = beanClass.getDeclaredField(name).getType();//获取 属性字段的类型
//                String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1); // 拼接set函数的name
//                Method method = beanClass.getDeclaredMethod(methodName,new Class[]{type});
//                method.invoke(bean,new Object[]{value});
                BeanUtil.setFieldValue(bean,name,value);

            }
        }catch (Exception e){
            throw new BeansException("Error setting property values for bean " + beanName,e);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition){
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
