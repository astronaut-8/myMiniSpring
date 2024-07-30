package org.springframework.aop;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} 抽象切点
 */
public interface Pointcut {
    /**
     *作为切点表达式，给外界暴露切点的类和方法匹配器
     */
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
