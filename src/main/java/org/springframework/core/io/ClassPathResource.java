package org.springframework.core.io;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} 类路径下的资源
 */
public class ClassPathResource implements Resource{
    private final String path;

    public ClassPathResource(String path){
        this.path = path;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);//获取类加载器读取对应路径上的信息转变为流
        if (is == null){
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exists");
        }
        return is;
    }



}
