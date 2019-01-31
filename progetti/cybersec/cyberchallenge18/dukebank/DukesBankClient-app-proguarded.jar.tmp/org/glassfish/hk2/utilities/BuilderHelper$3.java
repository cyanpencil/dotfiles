// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BuilderHelper.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.glassfish.hk2.utilities:
//            BuilderHelper

static class val.obj
    implements ServiceHandle
{

            public final Object getService()
            {
/* 656*/        return val$obj;
            }

            public final ActiveDescriptor getActiveDescriptor()
            {
/* 661*/        return null;
            }

            public final boolean isActive()
            {
/* 666*/        return true;
            }

            public final void destroy()
            {
            }

            public final synchronized void setServiceData(Object obj1)
            {
/* 676*/        serviceData = obj1;
            }

            public final synchronized Object getServiceData()
            {
/* 682*/        return serviceData;
            }

            private Object serviceData;
            final Object val$obj;

            (Object obj1)
            {
/* 651*/        val$obj = obj1;
/* 651*/        super();
            }
}
