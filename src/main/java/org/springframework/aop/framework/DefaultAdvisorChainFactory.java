package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.PointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/8/9
 * {@code @msg} 把AdviceSupport中advisors列表 ， 合法数据转换为MethodInterceptor的list
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory {
    @Override
    public List<Object> getInterceptorAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {
        Advisor[] advisors = config.getAdvisors().toArray(new Advisor[0]);
        List<Object> interceptorList = new ArrayList<>(advisors.length);
        Class<?> actualClass = targetClass != null ? targetClass : method.getDeclaringClass();
        for (Advisor advisor : advisors) {
            if (advisor instanceof PointcutAdvisor) {
                PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
                MethodMatcher mm = pointcutAdvisor.getPointCut().getMethodMatcher();
                boolean match;
                match = mm.matches(method, actualClass);
                if (match) {
                    MethodInterceptor interceptor = (MethodInterceptor) advisor.getAdvice();
                    interceptorList.add(interceptor);
                }
            }
        }
            return interceptorList;
    }
}
