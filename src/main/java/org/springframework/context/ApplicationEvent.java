package org.springframework.context;

import java.util.EventObject;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}
