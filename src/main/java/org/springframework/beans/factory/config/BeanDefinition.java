package org.springframework.beans.factory.config;

import org.springframework.beans.PropertyValues;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class BeanDefinition {
    //beanDefinition保存bean的信息，包括class类型、方法构造参数、是否为单例等

    private static String SCOPE_SINGLETON = "singleton";
    private static String SCOPE_PROTOTYPE = "prototype";
    private String scope = SCOPE_SINGLETON;
    private boolean singleton = true;
    private boolean prototype = false;
    private Class beanClass;

    private PropertyValues propertyValues;
    public BeanDefinition(Class beanClass){
        this(beanClass,null);
    }

    private String initMethodName;
    private String destroyMethodName;

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public void setScope(String scope){
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isPrototype() {
        return this.prototype;
    }
}
