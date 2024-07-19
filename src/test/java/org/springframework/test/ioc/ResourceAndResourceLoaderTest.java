package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.InputStream;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/11
 * {@code @msg} reserved
 */
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception{
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        //加载classpath下的资源
        Resource resource = resourceLoader.getResource("classpath:spring.xml");
        InputStream inputStream = resource.getInputStream();
        String context = IoUtil.readUtf8(inputStream);
        System.out.println("classpath ---> " + context);

        //加载文件系统资源
        resource = resourceLoader.getResource("src/test/resources/hello.txt");
        System.out.println(resource instanceof FileSystemResource);
        inputStream = resource.getInputStream();
        context = IoUtil.readUtf8(inputStream);
        System.out.println("fileSystem ---> " + context);

        //加载url资源
        resource = resourceLoader.getResource("https://www.baidu.com");
        System.out.println(resource instanceof UrlResource);
        inputStream = resource.getInputStream();
        context = IoUtil.readUtf8(inputStream);
        System.out.println("url ---> " + context);
    }
}
