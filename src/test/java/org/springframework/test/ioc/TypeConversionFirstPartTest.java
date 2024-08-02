package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.convert.support.StringToNumberConverterFactory;
import org.springframework.test.common.StringToBooleanConverter;
import org.springframework.test.common.StringToIntegerConverter;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/2
 * {@code @msg} reserved
 */
public class TypeConversionFirstPartTest {
    @Test
    public void testStringToIntegerConverter() throws Exception{
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("8888");
        System.out.println("text1 -> " + num);
    }
    @Test
    public void testStringToNumberConverterFactory() throws Exception{
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> converter1 = converterFactory.getConverter(Integer.class);
        Integer intNum = converter1.convert("8888");
        System.out.println("test2" + intNum);

        Converter<String, Long> converter2 = converterFactory.getConverter(Long.class);
        Long convert = converter2.convert("8888");
        System.out.println("test2" + convert);
    }

    @Test
    public void testGenericConverter() throws Exception{
        StringToBooleanConverter converter = new StringToBooleanConverter();
        Boolean aTrue = (Boolean) converter.convert("true", String.class, Boolean.class);
        System.out.println("test3 -> " + aTrue);
    }
    @Test
    public void testGenericConversionService() throws Exception{
        GenericConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(new StringToIntegerConverter());

        System.out.println(conversionService.canConvert(String.class,Integer.class));
        Integer intNum = conversionService.convert("8888", Integer.class);
        System.out.println(intNum);

        conversionService.addConverterFactory(new StringToNumberConverterFactory());
        System.out.println(conversionService.canConvert(String.class,Long.class));
        System.out.println(conversionService.convert("8888",Long.class));
    }
}
