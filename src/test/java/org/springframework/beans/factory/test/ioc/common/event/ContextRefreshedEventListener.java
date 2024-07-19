package org.springframework.beans.factory.test.ioc.common.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
