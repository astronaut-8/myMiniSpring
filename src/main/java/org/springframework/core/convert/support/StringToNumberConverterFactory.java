package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} reserved
 */
public class StringToNumberConverterFactory implements ConverterFactory<String ,Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<T>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String , T> {
        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()){
                return null;
            }
            if (targetType.equals(Integer.class)){
                return (T) Integer.valueOf(source);
            }else if(targetType.equals(Long.class)){
                return (T) Long.valueOf(source);
            }
            throw new IllegalArgumentException("cannot convert");
        }
    }
}
