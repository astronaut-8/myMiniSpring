package org.springframework.beans.factory.config;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public interface SingletonBeanRegistry {
    //单例注册表
    Object getSingleton(String name);
}
