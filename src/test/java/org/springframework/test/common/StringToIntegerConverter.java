package org.springframework.test.common;

import org.springframework.core.convert.converter.Converter;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/2
 * {@code @msg} reserved
 */
public class StringToIntegerConverter implements Converter<String,Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
