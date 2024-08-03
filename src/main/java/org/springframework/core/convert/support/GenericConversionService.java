package org.springframework.core.convert.support;

import cn.hutool.core.convert.BasicType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/2
 * {@code @msg} reserved
 */
public class GenericConversionService implements ConversionService , ConverterRegistry {
    private Map<ConvertiblePair, GenericConverter> converters = new HashMap<>();
    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return getConverter(sourceType , targetType) != null;
    }

    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourceCandidate : sourceCandidates){
            for (Class<?> targetCandidate : targetCandidates){
                ConvertiblePair convertiblePair = new ConvertiblePair(sourceCandidate , targetCandidate);
                GenericConverter converter = converters.get(convertiblePair);
                if (converter != null){
                    return converter;
                }
            }
        }
        return null;
    }

    /**
     *  获取superClass组
     */
    private List<Class<?>> getClassHierarchy(Class<?> clazz) {
        List<Class<?>> hierarchy = new ArrayList<>();
        clazz = BasicType.wrap(clazz); // 原始类转为包装类
        while (clazz != null){
            hierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return hierarchy;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        Class<?> sourceType = source.getClass();
        targetType = (Class<T>) BasicType.wrap(targetType); //转为包装类
        GenericConverter converter = getConverter(sourceType,targetType);
        return (T) converter.convert(source,sourceType,targetType);
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        ConvertiblePair typeInfo = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(typeInfo,converter);
        for (ConvertiblePair convertiblePair : converterAdapter.getConvertibleTypes()){

            converters.put(convertiblePair,converterAdapter);
        }
    }

    private ConvertiblePair getRequiredTypeInfo(Object converter) {
        Type[] types = converter.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0]; //<?,?>
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class sourceType = (Class) actualTypeArguments[0];
        Class targetType = (Class) actualTypeArguments[1];
        return new ConvertiblePair(sourceType,targetType);
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        ConvertiblePair typeInfo = getRequiredTypeInfo(converterFactory);
        ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(typeInfo , converterFactory);
        for (ConvertiblePair convertiblePair : converterFactoryAdapter.getConvertibleTypes()){
            converters.put(convertiblePair , converterFactoryAdapter);
        }
    }

    @Override
    public void addConverter(GenericConverter converter) {
        converter.getConvertibleTypes().forEach(convertiblePair -> converters.put(convertiblePair,converter));
    }

    private final class ConverterAdapter implements GenericConverter {
        private final ConvertiblePair typeInfo;
        private final Converter<Object,Object> converter;

        public ConverterAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converter.convert(source);
        }
    }

    private final class ConverterFactoryAdapter implements GenericConverter {
        private final ConvertiblePair typeInfo;
        private final ConverterFactory<Object,Object> converterFactory;

        public ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }
}
