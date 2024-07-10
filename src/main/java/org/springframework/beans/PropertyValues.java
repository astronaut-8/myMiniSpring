package org.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();
    public void addPropertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);//新建一个长度为0的数组，是因为本身不想要这个数组，只是为了提供这个变量类型
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : propertyValueList){
            if (pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;//不存在的情况
    }
}
