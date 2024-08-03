package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.ConverterRegistry;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/2
 * {@code @msg} reserved
 */
public class DefaultConversionService extends GenericConversionService{
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    private void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
