// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorUtilities.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.IndexedFilter;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl, ServiceLocatorUtilities

static class val.name
    implements IndexedFilter
{

            public final boolean matches(Descriptor descriptor)
            {
/* 554*/        return val$di.equals(descriptor);
            }

            public final String getAdvertisedContract()
            {
/* 559*/        return val$contract;
            }

            public final String getName()
            {
/* 564*/        return val$name;
            }

            final DescriptorImpl val$di;
            final String val$contract;
            final String val$name;

            (DescriptorImpl descriptorimpl, String s, String s1)
            {
/* 550*/        val$di = descriptorimpl;
/* 550*/        val$contract = s;
/* 550*/        val$name = s1;
/* 550*/        super();
            }
}
