package com.protonmail.landrevillejf.cognos.categories.api;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

@SuppressWarnings("CheckStyle")
public class SpringApplicationContext implements ApplicationContextAware {
    /**
     *
     */
    private static ApplicationContext context;

    /**
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(final @NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    /**
     *
     * @param beanClass
     * @return
     * @param <T>
     */
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
