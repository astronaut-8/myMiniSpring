package org.springframework.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.*;
import org.springframework.aop.aspectJ.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/25
 * {@code @msg} reserved
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor , BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
    private Set<Object> earlyProxyReferences = new HashSet<>();

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        if (! earlyProxyReferences.contains(beanName)){
            return wrapIfNecessary(bean , beanName);
        }
        return bean;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean , beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }
    private boolean isInfrastructureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public boolean postProcessorAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }
    protected Object wrapIfNecessary(Object bean , String beanName) {

        //避免死循环
        if (isInfrastructureClass(bean.getClass())){
            return bean;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeanOfType(AspectJExpressionPointcutAdvisor.class).values();
        try{
            ProxyFactory proxyFactory = new ProxyFactory();
            for (AspectJExpressionPointcutAdvisor advisor : advisors){
                ClassFilter classFilter = advisor.getPointCut().getClassFilter();
                if (classFilter.matches(bean.getClass())){
                    TargetSource targetSource = new TargetSource(bean);
                    proxyFactory.setTargetSource(targetSource);
                    proxyFactory.addAdvisor(advisor);
                    proxyFactory.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
                }
            }
            if (!proxyFactory.getAdvisors().isEmpty()) {
                return proxyFactory.getProxy();
            }
        }catch (Exception e){
            throw new BeansException("error create proxy bean for " + beanName,e);
        }
        return bean;
    }
}
