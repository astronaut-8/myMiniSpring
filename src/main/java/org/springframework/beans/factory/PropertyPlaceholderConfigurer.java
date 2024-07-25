package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/25
 * {@code @msg} reserved
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    public static final String PLACEHOLDER_PREFIX = "${";
    public static final String PLACEHOLDER_SUFFIX = "}";
    private String location;
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //加载属性配置文件
        Properties properties = loadProperties();

        //属性值替换占位符
        processProperties(beanFactory,properties);

    }

    private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException{
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames){
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            resolvePropertyValues(beanDefinition,properties);
        }
    }

    private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()){
            Object value = propertyValue.getValue();
            if (value instanceof String){
                //TODO 仅仅简单支持一个占位符格式
                String strVal = (String) value;
                StringBuffer buf = new StringBuffer(strVal);
                int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
                int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX);
                if (startIndex != -1 && endIndex != -1 && startIndex < endIndex){
                    String proKey = strVal.substring(startIndex + 2 , endIndex);
                    String proVal = properties.getProperty(proKey); //取出配置文件中的值
                    buf.replace(startIndex,endIndex+1,proVal);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),buf.toString()));
                }
            }
        }
    }

    private Properties loadProperties(){
        try{
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return properties;
        }catch (IOException e){
            throw new BeansException("Could not load properties",e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
