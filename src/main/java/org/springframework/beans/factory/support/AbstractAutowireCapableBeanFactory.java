package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.*;
import org.springframework.core.convert.ConversionService;

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
        //InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
        Object bean = resolveBeforeInstantiation(beanName,beanDefinition);
        if (bean != null){
            return bean;
        }
        return doCreateBean(beanName,beanDefinition);
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorBeforeInitialization(beanDefinition.getBeanClass(),beanName);
        if (bean != null){
            bean = applyBeanPostProcessorAfterInitialization(bean,beanName);
        }
        return bean;
    }
    protected Object applyBeanPostProcessorBeforeInitialization(Class beanClass ,String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object result = ((InstantiationAwareBeanPostProcessor)beanPostProcessor).postProcessBeforeInstantiation(beanClass,beanName);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {

        Object bean;
        try{
            bean = createBeanInstance(beanDefinition);

            //为解决循环依赖，在bean实例坏之后放入缓存提前暴露
            if (beanDefinition.isSingleton()){
                Object finalBean = bean;
                addSingletonFactory(beanName, new ObjectFactory<Object>() {
                    @Override
                    public Object getObject() throws BeansException {
                        return getEarlyBeanReference(beanName ,  beanDefinition , finalBean);
                    }
                });
            }


            //实例化bean之后执行
            //InstantiationAwareBeanPostProcessor#postProcessorAfterInstantiation
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName,bean);
            if (!continueWithPropertyPopulation){
                return bean;
            }
            //允许BeanPostProcessor修改属性 注解内容翻译到beanDefinition中去
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName,bean,beanDefinition);

            //为bean填充信息
            applyPropertyValues(beanName,bean,beanDefinition);
            //执行bean的初始化方法和BeanPostProcessor的前置和后置的方法
            bean = initializeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }

        //注册有销毁方法的bean
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);


        Object exposedObject = bean;
        if (beanDefinition.isSingleton()){
            //如果有代理对象，此处获取对象
            exposedObject = getSingleton(beanName);
            addSingleton(beanName,exposedObject);
        }

        return exposedObject;
    }
    private Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean) {
        Object exposedObject = bean;
        for (BeanPostProcessor bp : getBeanPostProcessors()){
            if (bp instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) bp).getEarlyBeanReference(exposedObject,beanName);
                if (exposedObject == null){
                    return exposedObject;
                }
            }
        }
        return exposedObject;
    }

    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
          for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
              if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                  if (!((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessorAfterInstantiation(bean,beanName)){
                      continueWithPropertyPopulation = false;
                      break;
                  }
              }
          }
          return continueWithPropertyPopulation;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        //只有单利bean会执行销毁方法
        if (beanDefinition.isSingleton()) {
            if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
                registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
            }
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
                }else{
                    //类型转换
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(),name);
                    ConversionService conversionService = getConversionService();
                    if (conversionService != null){
                        if (conversionService.canConvert(sourceType,targetType)){
                            value = conversionService.convert(value,targetType);
                        }
                    }
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

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName,Object bean , BeanDefinition beanDefinition){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor)beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(),bean,beanName);
                if (pvs != null){
                    for (PropertyValue propertyValue : pvs.getPropertyValues()){
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

}
