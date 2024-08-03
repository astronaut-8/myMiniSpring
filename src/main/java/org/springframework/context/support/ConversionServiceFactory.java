package org.springframework.context.support;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/3
 * {@code @msg} reserved
 */
public class ConversionServiceFactory implements FactoryBean<ConversionService> , InitializingBean {
    private Set<?> converters;
    private GenericConversionService conversionService;
    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        conversionService = new DefaultConversionService();
        registerConverters(converters , conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null){
            converters.forEach(converter -> {
                if (converter instanceof GenericConverter){
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?,?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?,?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                }else{
                    throw new IllegalArgumentException("illegal converter");
                }
            });
        }
    }
}
