package org.springframework.aop;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} 被代理对象的封装
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }
    public Object getTarget(){
        return this.target;
    }
}
