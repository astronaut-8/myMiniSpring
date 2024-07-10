package org.springframework.beans.factory.test.ioc;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.test.ioc.bean.Car;
import org.springframework.beans.factory.test.ioc.bean.Person;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */
public class PopulateBeanWithPropertyValuesTest {
    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","xiaoFu"));
        propertyValues.addPropertyValue(new PropertyValue("age",18));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinition("person",beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    @Test
    public void testPopulateBeanWithBean() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValuesForCar = new PropertyValues();
        propertyValuesForCar.addPropertyValue(new PropertyValue("brand","porsche"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class,propertyValuesForCar);
        beanFactory.registerBeanDefinition("car",carBeanDefinition);

        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name","fu"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age",18));

        propertyValuesForPerson.addPropertyValue(new PropertyValue("car",new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class,propertyValuesForPerson);
        beanFactory.registerBeanDefinition("person",personBeanDefinition);


        Person person = (Person) beanFactory.getBean("person");

        System.out.println(person.toString());




    }
}
