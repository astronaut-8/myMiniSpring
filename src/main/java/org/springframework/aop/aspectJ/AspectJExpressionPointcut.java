package org.springframework.aop.aspectJ;


import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/19
 * {@code @msg} reserved
 */

/**
 * AspectJ是一种切点表达式，就是切点(继承Pointcut) 用来，描述织入点，那么其基本作用
 * 就是以aspectJ的方式去解析这一切点匹配规则(这里是简单利用execution语句)
 * 作为一个匹配原则，其暴露出去的就是方法和类的匹配器，用来让外界去比匹配切点规则
 * 实现两个接口，可以做class和method的匹配
 */
public class AspectJExpressionPointcut implements Pointcut , ClassFilter , MethodMatcher {
    private final PointcutExpression pointcutExpression;

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();//存储切点的基本元素
    static{
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);//当前的切点表达式支持 execution 这一基本切点原语
    }
    public AspectJExpressionPointcut(String expression){
        //创建一个切点解析器
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES,this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
