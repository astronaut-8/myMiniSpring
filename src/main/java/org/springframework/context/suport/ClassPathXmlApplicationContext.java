package org.springframework.context.suport;

import org.springframework.beans.BeansException;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/14
 * {@code @msg} reserved
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }

}
