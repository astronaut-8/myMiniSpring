package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class ProxyFactory extends AdvisedSupport {

    public ProxyFactory() {
    }

    public Object getProxy(){
        return createProxy().getProxy();
    }

    private AopProxy createProxy(){
        if (this.isProxyTargetClass() || this.getTargetSource().getTargetClass().length == 0){
            return new CglibAopProxy(this);
        }
        return new JdkDynamicAopProxy(this);
    }
}
