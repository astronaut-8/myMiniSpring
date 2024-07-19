package org.springframework.context.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplication(){
        return (ApplicationContext) getSource();
    }
}
