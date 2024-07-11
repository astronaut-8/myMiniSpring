package org.springframework.core.io;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/11
 * {@code @msg}资源加载器接口
 */
public interface ResourceLoader {
    Resource getResource(String location);
}
