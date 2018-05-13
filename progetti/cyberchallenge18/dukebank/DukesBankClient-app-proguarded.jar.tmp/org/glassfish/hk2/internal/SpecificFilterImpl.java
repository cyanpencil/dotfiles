// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SpecificFilterImpl.java

package org.glassfish.hk2.internal;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.IndexedFilter;

public class SpecificFilterImpl
    implements IndexedFilter
{

            public SpecificFilterImpl(String s, String s1, long l, long l1)
            {
/*  68*/        contract = s;
/*  69*/        name = s1;
/*  70*/        id = l;
/*  71*/        locatorId = l1;
            }

            public boolean matches(Descriptor descriptor)
            {
/*  79*/        return descriptor.getServiceId().longValue() == id && descriptor.getLocatorId().longValue() == locatorId;
            }

            public String getAdvertisedContract()
            {
/*  92*/        return contract;
            }

            public String getName()
            {
/* 100*/        return name;
            }

            private final String contract;
            private final String name;
            private final long id;
            private final long locatorId;
}
