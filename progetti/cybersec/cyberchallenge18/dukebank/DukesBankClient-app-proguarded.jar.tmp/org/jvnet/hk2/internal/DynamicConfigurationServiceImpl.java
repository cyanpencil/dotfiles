// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DynamicConfigurationServiceImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            DynamicConfigurationImpl, PopulatorImpl, ServiceLocatorImpl

public class DynamicConfigurationServiceImpl
    implements DynamicConfigurationService
{

            private DynamicConfigurationServiceImpl(ServiceLocator servicelocator)
            {
/*  63*/        locator = (ServiceLocatorImpl)servicelocator;
/*  64*/        populator = new PopulatorImpl(servicelocator, this);
            }

            public DynamicConfiguration createDynamicConfiguration()
            {
/*  72*/        return new DynamicConfigurationImpl(locator);
            }

            public Populator getPopulator()
            {
/*  77*/        return populator;
            }

            private final ServiceLocatorImpl locator;
            private final Populator populator;
}
