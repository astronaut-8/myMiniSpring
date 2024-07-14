package org.springframework.context;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.io.ResourceLoader;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/14
 * {@code @msg} applicationContext
 */
public interface ApplicationContext extends ListableBeanFactory , HierarchicalBeanFactory , ResourceLoader {
}
