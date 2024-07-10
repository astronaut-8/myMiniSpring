package org.springframework.beans;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/6/30
 * {@code @msg} reserved
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg){
        super(msg);
    }
    public BeansException(String msg,Throwable cause){ //cause可以记录理解和追踪问题的根源 当一个异常是由另一个异常引起时，cause 参数用于捕获并存储原始异常
        super(msg,cause);
    }
}
