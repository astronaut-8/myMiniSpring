package org.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/11
 * {@code @msg} 根据字符串意义从三种resource读取方式中选择一种返回
 */
public class DefaultResourceLoader implements ResourceLoader{
    private static final String CLASSPATH_URL_PREFIX = "classpath:";
    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else{
            try{
                //尝试把这个字符串当成URL
                URL url = new URL(location);
                return new UrlResource(url);
            }catch (MalformedURLException e){
                String path = location;
                if (location.startsWith("/")){
                    path = location.substring(1);
                }
                return new FileSystemResource(path);
            }
        }

    }
}
