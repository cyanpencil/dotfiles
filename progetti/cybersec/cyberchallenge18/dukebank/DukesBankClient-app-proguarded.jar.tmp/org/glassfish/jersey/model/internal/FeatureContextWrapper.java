// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FeatureContextWrapper.java

package org.glassfish.jersey.model.internal;

import java.util.Map;
import javax.ws.rs.core.*;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;

public class FeatureContextWrapper
    implements FeatureContext, ServiceLocatorSupplier
{

            public FeatureContextWrapper(FeatureContext featurecontext, ServiceLocator servicelocator)
            {
/*  70*/        context = featurecontext;
/*  71*/        serviceLocator = servicelocator;
            }

            public Configuration getConfiguration()
            {
/*  76*/        return context.getConfiguration();
            }

            public FeatureContext property(String s, Object obj)
            {
/*  81*/        return (FeatureContext)context.property(s, obj);
            }

            public FeatureContext register(Class class1)
            {
/*  86*/        return (FeatureContext)context.register(class1);
            }

            public FeatureContext register(Class class1, int i)
            {
/*  91*/        return (FeatureContext)context.register(class1, i);
            }

            public transient FeatureContext register(Class class1, Class aclass[])
            {
/*  96*/        return (FeatureContext)context.register(class1, aclass);
            }

            public FeatureContext register(Class class1, Map map)
            {
/* 101*/        return (FeatureContext)context.register(class1, map);
            }

            public FeatureContext register(Object obj)
            {
/* 106*/        return (FeatureContext)context.register(obj);
            }

            public FeatureContext register(Object obj, int i)
            {
/* 111*/        return (FeatureContext)context.register(obj, i);
            }

            public transient FeatureContext register(Object obj, Class aclass[])
            {
/* 116*/        return (FeatureContext)context.register(obj, aclass);
            }

            public FeatureContext register(Object obj, Map map)
            {
/* 121*/        return (FeatureContext)context.register(obj, map);
            }

            public ServiceLocator getServiceLocator()
            {
/* 126*/        return serviceLocator;
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  58*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  58*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  58*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  58*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  58*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  58*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  58*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  58*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  58*/        return property(s, obj);
            }

            private final FeatureContext context;
            private final ServiceLocator serviceLocator;
}
