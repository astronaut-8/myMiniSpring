package org.springframework.context;

import org.springframework.beans.BeansException;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/14
 * {@code @msg} reserved
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     * @throws BeansException 异常
     */
    void refresh() throws BeansException;
}
