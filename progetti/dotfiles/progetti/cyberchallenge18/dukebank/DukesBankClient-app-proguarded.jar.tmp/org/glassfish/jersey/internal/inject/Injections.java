// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Injections.java

package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.*;

public class Injections
{

            public Injections()
            {
            }

            public static DynamicConfiguration getConfiguration(ServiceLocator servicelocator)
            {
/*  77*/        return (servicelocator = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0])).createDynamicConfiguration();
            }

            public static transient ServiceLocator createLocator(String s, ServiceLocator servicelocator, Binder abinder[])
            {
/*  95*/        return _createLocator(s, servicelocator, abinder);
            }

            public static transient ServiceLocator createLocator(String s, Binder abinder[])
            {
/* 109*/        return _createLocator(s, null, abinder);
            }

            public static transient ServiceLocator createLocator(ServiceLocator servicelocator, Binder abinder[])
            {
/* 123*/        return _createLocator(null, servicelocator, abinder);
            }

            public static transient ServiceLocator createLocator(Binder abinder[])
            {
/* 133*/        return _createLocator(null, null, abinder);
            }

            private static transient ServiceLocator _createLocator(String s, ServiceLocator servicelocator, Binder abinder[])
            {
/* 138*/        (s = factory.create(s, servicelocator, null, org.glassfish.hk2.api.ServiceLocatorFactory.CreatePolicy.DESTROY)).setNeutralContextClassLoader(false);
/* 141*/        ServiceLocatorUtilities.enablePerThreadScope(s);
/* 146*/        abinder = (servicelocator = abinder).length;
/* 146*/        for(int i = 0; i < abinder; i++)
                {
/* 146*/            Binder binder = servicelocator[i];
/* 147*/            bind(s, binder);
                }

/* 149*/        return s;
            }

            private static void bind(ServiceLocator servicelocator, Binder binder)
            {
                Object obj;
/* 153*/        obj = ((DynamicConfigurationService) (obj = (DynamicConfigurationService)servicelocator.getService(org/glassfish/hk2/api/DynamicConfigurationService, new Annotation[0]))).createDynamicConfiguration();
/* 156*/        servicelocator.inject(binder);
/* 157*/        binder.bind(((DynamicConfiguration) (obj)));
/* 159*/        ((DynamicConfiguration) (obj)).commit();
            }

            public static Object getOrCreate(ServiceLocator servicelocator, Class class1)
            {
                Object obj;
/* 172*/        if((obj = servicelocator.getService(class1, new Annotation[0])) == null)
/* 173*/            return servicelocator.createAndInitialize(class1);
/* 173*/        return obj;
/* 174*/        JVM INSTR dup ;
/* 182*/        obj;
/* 182*/        getErrors();
/* 182*/        iterator();
/* 182*/        servicelocator;
/* 182*/        while(servicelocator.hasNext()) 
                {
/* 182*/            class1 = (Throwable)servicelocator.next();
/* 183*/            if(javax/ws/rs/WebApplicationException.isAssignableFrom(class1.getClass()))
/* 184*/                throw (WebApplicationException)class1;
                }
/* 188*/        throw obj;
            }

            public static void addBinding(BindingBuilder bindingbuilder, DynamicConfiguration dynamicconfiguration)
            {
/* 199*/        BindingBuilderFactory.addBinding(bindingbuilder, dynamicconfiguration);
            }

            public static void addBinding(BindingBuilder bindingbuilder, DynamicConfiguration dynamicconfiguration, HK2Loader hk2loader)
            {
/* 213*/        BindingBuilderFactory.addBinding(bindingbuilder, dynamicconfiguration, hk2loader);
            }

            public static ServiceBindingBuilder newFactoryBinder(Class class1, Class class2)
            {
/* 226*/        return BindingBuilderFactory.newFactoryBinder(class1, class2);
            }

            public static ServiceBindingBuilder newFactoryBinder(Class class1)
            {
/* 239*/        return BindingBuilderFactory.newFactoryBinder(class1);
            }

            public static ServiceBindingBuilder newFactoryBinder(Factory factory1)
            {
/* 250*/        return BindingBuilderFactory.newFactoryBinder(factory1);
            }

            public static ServiceBindingBuilder newBinder(Class class1)
            {
/* 263*/        return BindingBuilderFactory.newBinder(class1);
            }

            public static ScopedBindingBuilder newBinder(Object obj)
            {
/* 277*/        return BindingBuilderFactory.newBinder(obj);
            }

            public static void shutdownLocator(ServiceLocator servicelocator)
            {
/* 287*/        if(factory.find(servicelocator.getName()) != null)
                {
/* 288*/            factory.destroy(servicelocator.getName());
/* 288*/            return;
                } else
                {
/* 290*/            servicelocator.shutdown();
/* 292*/            return;
                }
            }

            private static final ServiceLocatorFactory factory = ServiceLocatorFactory.getInstance();

}
