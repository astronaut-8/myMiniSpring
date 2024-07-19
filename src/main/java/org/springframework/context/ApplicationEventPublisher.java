package org.springframework.context;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/18
 * {@code @msg} reserved
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
