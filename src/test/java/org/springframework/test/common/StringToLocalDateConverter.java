package org.springframework.test.common;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/3
 * {@code @msg} reserved
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private final DateTimeFormatter dateTimeFormatter;

    public StringToLocalDateConverter(String parten) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(parten);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source,dateTimeFormatter);
    }
}

