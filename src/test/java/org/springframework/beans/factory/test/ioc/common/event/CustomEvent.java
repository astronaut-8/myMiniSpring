package org.springframework.beans.factory.test.ioc.common.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
