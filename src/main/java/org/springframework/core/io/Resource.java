package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} 资源的抽象接口
 */
public interface Resource {
    boolean exists();
    File getFile() throws IOException;
    InputStream getInputStream() throws IOException;
    URL getURL() throws IOException;

}
