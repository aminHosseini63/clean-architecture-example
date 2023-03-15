package com.link_intersystems.ioc.declaration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanConfigBeanDeclarationLocator implements BeanDeclarationLocator {

    private BeanDeclaration beanConfigBeanDeclaration;
    private List<BeanDeclaration> beanDeclarations;

    public BeanConfigBeanDeclarationLocator(BeanDeclaration beanConfigBeanDeclaration) {
        this.beanConfigBeanDeclaration = beanConfigBeanDeclaration;
    }


    @Override
    public List<BeanDeclaration> getBeanDeclarations() {
        if (beanDeclarations == null) {
            List<BeanDeclaration> beanDeclarations = new ArrayList<>();

            Class<?> beanConfigBeanType = beanConfigBeanDeclaration.getBeanType();
            Method[] beanFactoryMethods = beanConfigBeanType.getDeclaredMethods();
            for (Method beanFactoryMethod : beanFactoryMethods) {
                if (beanFactoryMethod.getDeclaringClass().equals(Object.class)) {
                    continue;
                }

                Class<?> beanDeclarationType = beanFactoryMethod.getReturnType();

                if (Void.TYPE.equals(beanDeclarationType)) {
                    continue;
                }

                BeanConfigBeanDeclarationLocation location = new BeanConfigBeanDeclarationLocation(beanConfigBeanDeclaration);
                BeanDeclaration beanFactoryMethodBeanDeclaration = new BeanFactoryMethodBeanDeclaration(beanDeclarationType, beanFactoryMethod, location);
                beanDeclarations.add(beanFactoryMethodBeanDeclaration);

            }

            this.beanDeclarations = beanDeclarations;
        }

        return beanDeclarations;
    }

}