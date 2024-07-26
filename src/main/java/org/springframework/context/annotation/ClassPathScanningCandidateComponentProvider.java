package org.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/26
 * {@code @msg} reserved
 */
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackages){
        Set<BeanDefinition> candidates = new LinkedHashSet<>(); //维护插入顺序的set

        //扫描注解
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackages, Component.class);
        for (Class<?> clazz : classes){
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            candidates.add(beanDefinition);
        }
        return candidates;
    }
}
