package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/10
 * {@code @msg} reserved
 */
public abstract class AbstractResource implements Resource{
    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException("resource cannot be resolved to URl");
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException("resource cannot be resolved to absolute file path");
    }

    @Override
    public boolean exists() {
        try{
            return getFile().exists();
        }catch (IOException ex){
            try{
                InputStream is = getInputStream();
                is.close();
                return true;
            }catch (Throwable isEx){
                return false;
            }
        }

    }
}
