// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceHandleComparator.java

package org.jvnet.hk2.internal;

import java.io.Serializable;
import java.util.Comparator;
import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.jvnet.hk2.internal:
//            DescriptorComparator

public class ServiceHandleComparator
    implements Serializable, Comparator
{

            public ServiceHandleComparator()
            {
            }

            public int compare(ServiceHandle servicehandle, ServiceHandle servicehandle1)
            {
/*  65*/        return baseComparator.compare(servicehandle.getActiveDescriptor(), servicehandle1.getActiveDescriptor());
            }

            public volatile int compare(Object obj, Object obj1)
            {
/*  52*/        return compare((ServiceHandle)obj, (ServiceHandle)obj1);
            }

            private static final long serialVersionUID = 0xcfc436c81dc56915L;
            private final DescriptorComparator baseComparator = new DescriptorComparator();
}
