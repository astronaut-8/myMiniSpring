package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

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

        //注册有销毁方法的bean
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        addSingleton(beanName,bean);
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
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
        if (bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        //执行BeanPostProcessor的前置方法
        Object wrapperBean = applyBeanPostProcessorBeforeInitialization(bean,beanName);
        // bean的初始化方法
        try {
            invokeInitMethod(beanName, wrapperBean, beanDefinition);
        }catch (Throwable ex){
            throw new BeansException("invocation of init method of bean " + beanName + "failed",ex);
        }
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

    protected void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable{
       if (bean instanceof InitializingBean){
           ((InitializingBean)bean).afterPropertiesSet();
       }
       String initMethodName = beanDefinition.getInitMethodName();
       if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))){
           Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(),initMethodName);
           if (initMethod == null){
               throw new BeansException("Could not find an init method named " + initMethodName + "on bean with name" + beanName);
           }
           initMethod.invoke(bean);
       }
    }

}
