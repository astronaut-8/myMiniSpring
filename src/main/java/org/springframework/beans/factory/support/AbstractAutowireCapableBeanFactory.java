package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
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
            //执行bean的初始化方法和BeanPostProcessor的前置和后置的方法
            bean = initializeBean(beanName,bean,beanDefinition);
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

                if (value instanceof BeanReference){
                    //当bean依赖这个value当时候，先实例化value
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

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



    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        //执行BeanPostProcessor的前置方法
        Object wrapperBean = applyBeanPostProcessorBeforeInitialization(bean,beanName);
        //TODO bean的初始化方法
        invokeInitMethod(beanName,wrapperBean,beanDefinition);
        //执行BeanPostProcessor的后置方法
        wrapperBean = applyBeanPostProcessorAfterInitialization(bean,beanName);
        return wrapperBean;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessorAfterInitialization(result,beanName);
            if (current == null){
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessorBeforeInitialization(result,beanName);
            if (current == null){
                return result;
            }
            result = current;
        }
        return result;
    }

    private void invokeInitMethod(String beanName, Object wrapperBean, BeanDefinition beanDefinition) {
        System.out.println("执行bean的初始化方法");
    }
}
