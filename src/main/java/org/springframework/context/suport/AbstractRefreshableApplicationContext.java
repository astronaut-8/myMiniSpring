package org.springframework.context.suport;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/14
 * {@code @msg} reserved
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    public DefaultListableBeanFactory getBeanFactory(){
        return beanFactory;
    }
    protected DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;
}
