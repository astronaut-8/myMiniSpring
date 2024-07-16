package org.springframework.beans.factory;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/15
 * {@code @msg} reserved
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
