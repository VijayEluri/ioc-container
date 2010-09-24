package com.my.container.aop;

import com.my.container.aop.annotations.AroundInvoke;
import com.my.container.aop.annotations.Interceptors;
import com.my.container.context.beanfactory.BeanInstanceWeaver;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevinpollet
 */
public class AOPBeanInstanceWeaver extends BeanInstanceWeaver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidBean(final Object bean) {
        Class<?> beanClass = bean.getClass();
        return beanClass.isAnnotationPresent(Interceptors.class) || this.isAroundMethod(beanClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T weaveBean(final T bean) {
        Class<?> beanClass = bean.getClass();

        List<Object> interceptors = new ArrayList<Object>();
        Method aroundMethod = null;

        // Get around method
        Method[] methods = beanClass.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(AroundInvoke.class)) {
                aroundMethod = m;
                break;
            }
        }

        // Create interceptor
        if (beanClass.isAnnotationPresent(Interceptors.class)) {
            Interceptors annotation = beanClass.getAnnotation(Interceptors.class);
            Class<?>[] interceptorsClass = annotation.value();
            for (Class<?> intClass : interceptorsClass) {
                try {

                    interceptors.add(intClass.newInstance());

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            
        }

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                          beanClass.getInterfaces(),
                                          new InterceptorInvocationHandler(bean, interceptors.toArray(), aroundMethod));
    }


    /**
     * Check if the class in parameter have an
     * @AroundInvoke method.
     *
     * @param clazz the class
     * @return true if the class contains a method annotated with @AroundInvoke, false otherwise
     *
     */
    private boolean isAroundMethod(final Class<?> clazz) {
        boolean result = false;

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(AroundInvoke.class)) {
                result = true;
                break;
            }
        }

        return result;
    }


}
