/*
 * Copyright 2010 Kevin Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.my.container.context.beanfactory.injector;

import com.my.container.context.beanfactory.BeanFactory;

import javax.inject.Provider;

/**
 * The default provider. This provider is used
 * when a Provider is injected and the user provides
 * no custom provider.
 *
 * @author Kevin Pollet
 */
public class DefaultInstanceProvider<T> implements Provider<T> {

    private final BeanFactory factory;
    
    private final Class<T> classToCreate;

    /**
     * Construct an instance of default provider.
     *
     * @param factory the factory to create the injected instance
     * @param classToCreate the bean class to create on each {@link javax.inject.Provider#get()} method call
     */
    public DefaultInstanceProvider(final BeanFactory factory, final Class<T> classToCreate) {
        this.factory = factory;
        this.classToCreate = classToCreate;
    }

    /**
     * {@inheritDoc}
     */
    public T get() {
        return factory.getBean(this.classToCreate);
    }
}