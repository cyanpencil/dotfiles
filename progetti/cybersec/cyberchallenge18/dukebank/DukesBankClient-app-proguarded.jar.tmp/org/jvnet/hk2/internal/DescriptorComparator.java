// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorComparator.java

package org.jvnet.hk2.internal;

import java.io.Serializable;
import java.util.Comparator;
import org.glassfish.hk2.api.Descriptor;

public class DescriptorComparator
    implements Serializable, Comparator
{

            public DescriptorComparator()
            {
            }

            public int compare(Descriptor descriptor, Descriptor descriptor1)
            {
/*  64*/        int i = descriptor.getRanking();
/*  65*/        int j = descriptor1.getRanking();
/*  67*/        if(i < j)
/*  67*/            return 1;
/*  68*/        if(i > j)
/*  68*/            return -1;
/*  70*/        long l = descriptor.getLocatorId().longValue();
/*  71*/        long l1 = descriptor1.getLocatorId().longValue();
/*  73*/        if(l < l1)
/*  73*/            return 1;
/*  74*/        if(l > l1)
/*  74*/            return -1;
/*  76*/        long l2 = descriptor.getServiceId().longValue();
/*  77*/        long l3 = descriptor1.getServiceId().longValue();
/*  79*/        if(l2 > l3)
/*  79*/            return 1;
/*  80*/        return l2 >= l3 ? 0 : -1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
/*  52*/        return compare((Descriptor)obj, (Descriptor)obj1);
            }

            private static final long serialVersionUID = 0x3dd198729f0f4b7aL;
}
