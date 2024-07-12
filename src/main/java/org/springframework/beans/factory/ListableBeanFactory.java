package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/12
 * {@code @msg} listable 可以列出的  可以列出bean
 */
public interface ListableBeanFactory {
    /**
     * 返回指定类型的所有实例
     * @param type class
     * @return map
     * @param <T> 泛型
     * @throws BeansException 异常
     */
    <T> Map<String,T> getBeanOfType(Class<T> type) throws BeansException;
}
