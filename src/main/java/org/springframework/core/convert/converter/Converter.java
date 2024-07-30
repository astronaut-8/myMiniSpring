package org.springframework.core.convert.converter;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} 类型转换抽象接口
 */
public interface Converter<S , T> {
    T convert(S source);
}
