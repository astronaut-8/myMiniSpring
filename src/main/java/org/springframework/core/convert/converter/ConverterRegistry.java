package org.springframework.core.convert.converter;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} reserved
 */
public interface ConverterRegistry {
    void addConverter(Converter<?,?> converter);
    void addConverterFactory(ConverterFactory<? , ?> converterFactory);
    void addConverter(GenericConverter converter);
}
