// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutorProviders.java

package org.glassfish.jersey.process.internal;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.binding.ScopedBindingBuilder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.glassfish.jersey.spi.ScheduledExecutorServiceProvider;

public final class ExecutorProviders
{
    static class ScheduledExecutorServiceFactory
        implements Factory
    {

                public ScheduledExecutorService provide()
                {
/* 255*/            return executorProvider.getExecutorService();
                }

                public void dispose(ScheduledExecutorService scheduledexecutorservice)
                {
/* 260*/            executorProvider.dispose(scheduledexecutorservice);
                }

                public volatile void dispose(Object obj)
                {
/* 245*/            dispose((ScheduledExecutorService)obj);
                }

                public volatile Object provide()
                {
/* 245*/            return provide();
                }

                private final ScheduledExecutorServiceProvider executorProvider;

                private ScheduledExecutorServiceFactory(ScheduledExecutorServiceProvider scheduledexecutorserviceprovider)
                {
/* 250*/            executorProvider = scheduledexecutorserviceprovider;
                }

    }

    static class ExecutorServiceFactory
        implements Factory
    {

                public ExecutorService provide()
                {
/* 236*/            return executorProvider.getExecutorService();
                }

                public void dispose(ExecutorService executorservice)
                {
/* 241*/            executorProvider.dispose(executorservice);
                }

                public volatile void dispose(Object obj)
                {
/* 226*/            dispose((ExecutorService)obj);
                }

                public volatile Object provide()
                {
/* 226*/            return provide();
                }

                private final ExecutorServiceProvider executorProvider;

                private ExecutorServiceFactory(ExecutorServiceProvider executorserviceprovider)
                {
/* 231*/            executorProvider = executorserviceprovider;
                }

    }


            private ExecutorProviders()
            {
/*  83*/        throw new AssertionError("Instantiation not allowed.");
            }

            public static void createInjectionBindings(ServiceLocator servicelocator)
            {
/* 102*/        Map map = getQualifierToProviderMap(servicelocator, org/glassfish/jersey/spi/ExecutorServiceProvider);
/* 106*/        DynamicConfiguration dynamicconfiguration = Injections.getConfiguration(servicelocator);
/* 109*/        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
                {
                    java.util.Map.Entry entry;
/* 109*/            Object obj = (Class)(entry = (java.util.Map.Entry)iterator.next()).getKey();
                    Iterator iterator2;
/* 112*/            ExecutorServiceProvider executorserviceprovider = (ExecutorServiceProvider)(iterator2 = ((List)entry.getValue()).iterator()).next();
/* 114*/            if(LOGGER.isLoggable(Level.CONFIG))
                    {
/* 115*/                LOGGER.config(LocalizationMessages.USING_EXECUTOR_PROVIDER(executorserviceprovider.getClass().getName(), ((Class) (obj)).getName()));
/* 118*/                if(iterator2.hasNext())
                        {
/* 119*/                    StringBuilder stringbuilder = new StringBuilder(((ExecutorServiceProvider)iterator2.next()).getClass().getName());
/* 120*/                    for(; iterator2.hasNext(); stringbuilder.append(", ").append(((ExecutorServiceProvider)iterator2.next()).getClass().getName()));
/* 123*/                    LOGGER.config(LocalizationMessages.IGNORED_EXECUTOR_PROVIDERS(stringbuilder.toString(), ((Class) (obj)).getName()));
                        }
                    }
/* 128*/            ScopedBindingBuilder scopedbindingbuilder = Injections.newFactoryBinder(new ExecutorServiceFactory(executorserviceprovider)).to(java/util/concurrent/ExecutorService).in(javax/inject/Singleton);
/* 133*/            if((obj = executorserviceprovider.getClass().getAnnotation(((Class) (obj)))) instanceof Named)
/* 135*/                Injections.addBinding(scopedbindingbuilder.named(((Named)obj).value()), dynamicconfiguration);
/* 137*/            else
/* 137*/                Injections.addBinding(scopedbindingbuilder.qualifiedBy(((Annotation) (obj))), dynamicconfiguration);
                }

                Map map1;
/* 141*/        for(Iterator iterator1 = (map1 = getQualifierToProviderMap(servicelocator, org/glassfish/jersey/spi/ScheduledExecutorServiceProvider)).entrySet().iterator(); iterator1.hasNext();)
                {
                    Object obj1;
/* 145*/            Class class1 = (Class)((java.util.Map.Entry) (obj1 = (java.util.Map.Entry)iterator1.next())).getKey();
                    Iterator iterator3;
/* 148*/            ScheduledExecutorServiceProvider scheduledexecutorserviceprovider = (ScheduledExecutorServiceProvider)(iterator3 = ((List)((java.util.Map.Entry) (obj1)).getValue()).iterator()).next();
/* 150*/            if(LOGGER.isLoggable(Level.CONFIG))
                    {
/* 151*/                LOGGER.config(LocalizationMessages.USING_SCHEDULER_PROVIDER(scheduledexecutorserviceprovider.getClass().getName(), class1.getName()));
/* 154*/                if(iterator3.hasNext())
                        {
/* 155*/                    obj1 = new StringBuilder(((ScheduledExecutorServiceProvider)iterator3.next()).getClass().getName());
/* 156*/                    for(; iterator3.hasNext(); ((StringBuilder) (obj1)).append(", ").append(((ScheduledExecutorServiceProvider)iterator3.next()).getClass().getName()));
/* 159*/                    LOGGER.config(LocalizationMessages.IGNORED_SCHEDULER_PROVIDERS(((StringBuilder) (obj1)).toString(), class1.getName()));
                        }
                    }
/* 164*/            obj1 = Injections.newFactoryBinder(new ScheduledExecutorServiceFactory(scheduledexecutorserviceprovider)).in(javax/inject/Singleton).to(java/util/concurrent/ScheduledExecutorService);
/* 169*/            if(!map.containsKey(class1))
/* 171*/                ((ScopedBindingBuilder) (obj1)).to(java/util/concurrent/ExecutorService);
/* 174*/            if((servicelocator = scheduledexecutorserviceprovider.getClass().getAnnotation(class1)) instanceof Named)
/* 176*/                Injections.addBinding(((ScopedBindingBuilder) (obj1)).named(((Named)servicelocator).value()), dynamicconfiguration);
/* 178*/            else
/* 178*/                Injections.addBinding(((ScopedBindingBuilder) (obj1)).qualifiedBy(servicelocator), dynamicconfiguration);
                }

/* 182*/        dynamicconfiguration.commit();
            }

            private static Map getQualifierToProviderMap(ServiceLocator servicelocator, Class class1)
            {
/* 190*/        Set set = Providers.getCustomProviders(servicelocator, class1);
/* 192*/        (servicelocator = Providers.getProviders(servicelocator, class1)).removeAll(set);
/* 196*/        (class1 = new LinkedList(set)).addAll(servicelocator);
/* 198*/        servicelocator = class1.iterator();
/* 201*/        class1 = new HashMap();
/* 204*/        while(servicelocator.hasNext()) 
                {
                    ExecutorServiceProvider executorserviceprovider;
/* 205*/            Iterator iterator = ReflectionHelper.getAnnotationTypes((executorserviceprovider = (ExecutorServiceProvider)servicelocator.next()).getClass(), javax/inject/Qualifier).iterator();
/* 208*/            while(iterator.hasNext()) 
                    {
/* 208*/                Class class2 = (Class)iterator.next();
                        Object obj;
/* 211*/                if(!class1.containsKey(class2))
                        {
/* 212*/                    obj = new LinkedList();
/* 213*/                    class1.put(class2, obj);
                        } else
                        {
/* 215*/                    obj = (List)class1.get(class2);
                        }
/* 218*/                ((List) (obj)).add(executorserviceprovider);
                    }
                }
/* 222*/        return class1;
            }

            private static final ExtendedLogger LOGGER;

            static 
            {
/*  79*/        LOGGER = new ExtendedLogger(Logger.getLogger(org/glassfish/jersey/process/internal/ExecutorProviders.getName()), Level.FINEST);
            }
}
