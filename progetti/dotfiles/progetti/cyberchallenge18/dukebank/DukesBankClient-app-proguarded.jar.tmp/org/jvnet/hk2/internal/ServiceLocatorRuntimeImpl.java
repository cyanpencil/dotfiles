// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorRuntimeImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.external.runtime.ServiceLocatorRuntimeBean;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

public class ServiceLocatorRuntimeImpl
    implements ServiceLocatorRuntimeBean
{

            private ServiceLocatorRuntimeImpl(ServiceLocator servicelocator)
            {
/*  61*/        locator = (ServiceLocatorImpl)servicelocator;
            }

            public int getNumberOfDescriptors()
            {
/*  69*/        return locator.getNumberOfDescriptors();
            }

            public int getNumberOfChildren()
            {
/*  77*/        return locator.getNumberOfChildren();
            }

            public int getServiceCacheSize()
            {
/*  85*/        return locator.getServiceCacheSize();
            }

            public int getServiceCacheMaximumSize()
            {
/*  93*/        return locator.getServiceCacheMaximumSize();
            }

            public void clearServiceCache()
            {
/* 101*/        locator.clearServiceCache();
            }

            public int getReflectionCacheSize()
            {
/* 110*/        return locator.getReflectionCacheSize();
            }

            public void clearReflectionCache()
            {
/* 118*/        locator.clearReflectionCache();
            }

            private final ServiceLocatorImpl locator;
}
