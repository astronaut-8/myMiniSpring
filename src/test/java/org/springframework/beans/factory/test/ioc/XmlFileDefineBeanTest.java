package org.springframework.beans.factory.test.ioc;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.test.ioc.bean.Person;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/11
 * {@code @msg} reserved
 */
public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        try {
            beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        }catch (BeansException e){
            throw new BeansException("error",e);
        }

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
}
