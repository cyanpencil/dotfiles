// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorUtilities.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl, ServiceLocatorUtilities

static class val.di
    implements Filter
{

            public final boolean matches(Descriptor descriptor)
            {
/* 638*/        return val$di.equals(descriptor);
            }

            final DescriptorImpl val$di;

            (DescriptorImpl descriptorimpl)
            {
/* 634*/        val$di = descriptorimpl;
/* 634*/        super();
            }
}
