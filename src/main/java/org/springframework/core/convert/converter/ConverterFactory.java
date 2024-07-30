package org.springframework.core.convert.converter;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} 类型转换工厂
 */
public interface ConverterFactory<S , R> {
    <T extends R> Converter<S , T> getConverter(Class<T> targetType);
}
