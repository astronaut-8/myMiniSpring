package org.springframework.core.io;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} 资源的抽象接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
