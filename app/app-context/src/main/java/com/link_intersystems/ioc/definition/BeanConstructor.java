package com.link_intersystems.ioc.definition;

import com.link_intersystems.ioc.context.BeanFactory;

public interface BeanConstructor {

    public <T> T createBean(BeanFactory beanFactory) throws Exception;
}
