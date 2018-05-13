// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorGeneratorImpl.java

package org.jvnet.hk2.external.generator;

import javax.inject.Singleton;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.DescriptorBuilder;
import org.jvnet.hk2.internal.*;

public class ServiceLocatorGeneratorImpl
    implements ServiceLocatorGenerator
{

            public ServiceLocatorGeneratorImpl()
            {
            }

            private ServiceLocatorImpl initialize(String s, ServiceLocator servicelocator)
            {
/*  63*/        if(servicelocator != null && !(servicelocator instanceof ServiceLocatorImpl))
                {
/*  64*/            throw new AssertionError((new StringBuilder("parent must be a ")).append(org/jvnet/hk2/internal/ServiceLocatorImpl.getName()).append(" instead it is a ").append(servicelocator.getClass().getName()).toString());
                } else
                {
/*  68*/            s = new ServiceLocatorImpl(s, (ServiceLocatorImpl)servicelocator);
/*  70*/            (servicelocator = new DynamicConfigurationImpl(s)).bind(Utilities.getLocatorDescriptor(s));
/*  76*/            servicelocator.addActiveDescriptor(Utilities.getThreeThirtyDescriptor(s));
/*  79*/            servicelocator.bind(BuilderHelper.link(org/jvnet/hk2/internal/DynamicConfigurationServiceImpl, false).to(org/glassfish/hk2/api/DynamicConfigurationService).in(javax/inject/Singleton.getName()).localOnly().build());
/*  85*/            servicelocator.bind(BuilderHelper.createConstantDescriptor(new DefaultClassAnalyzer(s)));
/*  88*/            servicelocator.bind(BuilderHelper.createDescriptorFromClass(org/jvnet/hk2/internal/ServiceLocatorRuntimeImpl));
/*  90*/            servicelocator.bind(BuilderHelper.createConstantDescriptor(new InstantiationServiceImpl()));
/*  93*/            servicelocator.commit();
/*  95*/            return s;
                }
            }

            public ServiceLocator create(String s, ServiceLocator servicelocator)
            {
/* 103*/        return s = initialize(s, servicelocator);
            }

            public String toString()
            {
/* 110*/        return (new StringBuilder("ServiceLocatorGeneratorImpl(hk2-locator, ")).append(System.identityHashCode(this)).append(")").toString();
            }
}
