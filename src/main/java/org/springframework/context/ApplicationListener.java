package org.springframework.context;

import java.util.EventListener;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
