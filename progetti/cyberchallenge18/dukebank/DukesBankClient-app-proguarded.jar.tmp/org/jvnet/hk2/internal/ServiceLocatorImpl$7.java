// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

class this._cls0
    implements IndexedFilter
{

            public boolean matches(Descriptor descriptor)
            {
/* 891*/        return descriptor.getLocatorId().equals(Long.valueOf(ServiceLocatorImpl.access$300(ServiceLocatorImpl.this)));
            }

            public String getAdvertisedContract()
            {
/* 896*/        return org/glassfish/hk2/api/Context.getName();
            }

            public String getName()
            {
/* 901*/        return null;
            }

            final ServiceLocatorImpl this$0;

            ()
            {
/* 887*/        this$0 = ServiceLocatorImpl.this;
/* 887*/        super();
            }
}
