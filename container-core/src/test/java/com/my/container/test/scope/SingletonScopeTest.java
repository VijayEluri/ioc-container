package com.my.container.test.scope;

import com.my.container.binding.provider.BindingProvider;
import com.my.container.context.ApplicationContext;
import com.my.container.context.Context;
import com.my.container.env.services.HelloService;
import com.my.container.env.services.HelloServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the bean scope singleton.
 */
public class SingletonScopeTest {

    private Context context;

    @Before
    public void setUp() {
        this.context = new ApplicationContext(new BindingProvider(){
            @Override
            public void configureBindings() {
                bind(HelloService.class).to(HelloServiceImpl.class);
            }
        });

    }

    @Test
    public void testSingletonScope() {
        HelloService helloService = this.context.getBean(HelloService.class);
        HelloService helloService2 = this.context.getBean(HelloService.class);

        Assert.assertNotNull(helloService);
        Assert.assertNotNull(helloService2);
        Assert.assertTrue(helloService == helloService2);
    }
}