package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.test.common.event.CustomEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */
public class EventAndEventListenerTest {

    @Test
    public void testEvenListener() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));

        applicationContext.registerShutdownHook();
    }
}
