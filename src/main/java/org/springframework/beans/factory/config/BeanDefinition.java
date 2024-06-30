package org.springframework.beans.factory.config;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class BeanDefinition {
    //beanDefinition保存bean的信息，包括class类型、方法构造参数、是否为单例等，此处简化只包含class类型
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
