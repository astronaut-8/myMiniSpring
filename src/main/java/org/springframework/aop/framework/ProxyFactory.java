package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
    public Object getProxy(){
        return createProxy().getProxy();
    }

    private AopProxy createProxy(){
        if (advisedSupport.isProxyTargetClass()){
            return new CglibAopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
