package org.springframework.beans;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }



    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return "PropertyValue{name = " + name + ", value = " + value + "}";
    }
}
