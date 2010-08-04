package com.my.container.exceptions.beanfactory;

public abstract class BeanFactoryException extends RuntimeException {

    public BeanFactoryException(final String message) {
        super(message);
    }

    public BeanFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanFactoryException(final Throwable cause) {
        super(cause);
    }
}