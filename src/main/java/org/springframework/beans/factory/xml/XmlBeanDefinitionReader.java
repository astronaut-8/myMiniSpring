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


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
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
        }catch (Exception e){
            throw new BeansException("IOException parsing XML document from "+ resource,e);
        }
    }
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
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

                    Class<?> clazz = Class.forName(className); //将名字转换为class类

                    //id的优先级大于name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    if (StrUtil.isEmpty(beanName)){
                        //这种情况是 id 和 name都为空的情况
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());//取类名的第一个字母转为小写后的结果
                    }
                    BeanDefinition beanDefinition = new BeanDefinition(clazz); //创建bean定义信息

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

                                Object value = valueAttribute;
                                if (StrUtil.isNotEmpty(refAttribute)){
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute,value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }

                    getRegistry().registerBeanDefinition(beanName,beanDefinition);

                }
            }
        }
    }


}
