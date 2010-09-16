package com.my.container.test.injection.services.impl.fields;

import com.my.container.test.injection.services.ServiceC;
import com.my.container.test.injection.services.ServiceD;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author kevinpollet
 */
public class FieldServiceDImpl implements ServiceD {

    @Inject
    @Named("upperEchoService")
    private ServiceC serviceC;

    @Override
    public String echo(final String string) {
        return this.serviceC.echo(string);
    }

    @Override
    public int add(final int a, final int b) {
        return a+b;
    }
}