package com.my.container.test.callbacks;

import com.my.container.binding.provider.BindingProvider;
import com.my.container.context.ApplicationContext;
import com.my.container.context.Context;
import com.my.container.context.beanfactory.BeanFactory;
import com.my.container.test.callbacks.services.Service;
import com.my.container.test.callbacks.services.ServiceImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * The PreDestroy callback test.
 *
 * @author kevinpollet
 */
public class PreDestroyTest {

    private Context context;

    @Before
    public void setUp() {
        this.context = new ApplicationContext(new BindingProvider(){
            @Override
            public void configureBindings() {
                bind(Service.class).to(ServiceImpl.class);

            }

        });
    }

    @Test
    public void testPreDestroy() throws NoSuchFieldException, IllegalAccessException {
        Service service = this.context.getBean(Service.class);

        //Get the private bean factory
        Field factoryField = this.context.getClass().getDeclaredField("factory");
        factoryField.setAccessible(true);
        BeanFactory factory = (BeanFactory) factoryField.get(this.context);
        factory.removeAllBeansReferences();

        Assert.assertNotNull(service);
        Assert.assertNotNull(service);
        Assert.assertEquals("PreDestroy method not called or more than one times", 1, ((ServiceImpl) service).getNbCallPreDestroy());
    }

}
