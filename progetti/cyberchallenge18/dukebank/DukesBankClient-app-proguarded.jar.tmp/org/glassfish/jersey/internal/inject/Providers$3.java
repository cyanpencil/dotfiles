// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Providers.java

package org.glassfish.jersey.internal.inject;

import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.jersey.model.internal.RankedProvider;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            Providers

static class r
    implements Function
{

            public final Object apply(RankedProvider rankedprovider)
            {
/* 301*/        return rankedprovider.getProvider();
            }

            public final volatile Object apply(Object obj)
            {
/* 298*/        return apply((RankedProvider)obj);
            }

            r()
            {
            }
}
