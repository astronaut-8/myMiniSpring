package org.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/11
 * {@code @msg} reserved
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try{
            InputStream inputStream = resource.getInputStream();
            try{
                doLoadBeanDefinitions(inputStream);
            }finally {
                inputStream.close();
            }
        }catch (IOException e){
            throw new BeansException("IOException parsing XML document from "+ resource,e);
        }
    }
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
    protected void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream); // 将xml解析成document结构
        Element root = document.getDocumentElement();//这代表最大的bean标签
        NodeList childNodes = root.getChildNodes(); // 每一个元素代表这个标签下的子标签
        for (int i = 0 ; i < childNodes.getLength() ; i ++){
            if (childNodes.item(i) instanceof Element){ // 子标签还是一个element
                if (BEAN_ELEMENT.equals(((Element) childNodes.item(i)).getNodeName())){ // bean标签
                    //解析bean标签
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);

                    String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                    String destroyMethodName = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
                    Class<?> clazz = null; //将名字转换为class类
                    try{
                        clazz = Class.forName(className);
                    }catch (ClassNotFoundException e){
                        throw new BeansException("Cannot find class - " + className);
                    }
                    //id的优先级大于name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    if (StrUtil.isEmpty(beanName)){
                        //这种情况是 id 和 name都为空的情况
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());//取类名的第一个字母转为小写后的结果
                    }
                    BeanDefinition beanDefinition = new BeanDefinition(clazz); //创建bean定义信息
                    beanDefinition.setInitMethodName(initMethodName);
                    beanDefinition.setDestroyMethodName(destroyMethodName);

                    //填充xml中bean定义的属性
                    for (int j = 0; j < bean.getChildNodes().getLength() ;j ++){
                        if (bean.getChildNodes().item(j) instanceof Element){
                            //代表是一个property标签
                            if (PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())){
                                //解析property标签
                                Element property = (Element) bean.getChildNodes().item(j);
                                String  nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);
                                if (StrUtil.isEmpty(nameAttribute)){
                                    throw new BeansException("the name attribute cannot be null or empty");
                                }

                                Object value = valueAttribute;
                                if (StrUtil.isNotEmpty(refAttribute)){
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute,value);

                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);

                            }
                        }
                    }

                    if (getRegistry().containsBeanDefinition(beanName)){
                        throw new BeansException("Duplicate beanName " + beanName + "is not allowed");
                    }

                    getRegistry().registerBeanDefinition(beanName,beanDefinition);

                }
            }
        }
    }


}
