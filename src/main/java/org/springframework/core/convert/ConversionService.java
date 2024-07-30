package org.springframework.core.convert;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} reserved
 */
public interface ConversionService {
    boolean canConvert(Class<?> sourceType , Class<?> targetType);
    <T> T convert(Object source,Class<T> targetType);
}
