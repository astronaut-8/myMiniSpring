package org.springframework.aop.aspectJ;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/21
 * {@code @msg} reserved
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private AspectJExpressionPointcut pointcut;
    private Advice advice;
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointCut() {
        if (pointcut == null){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
