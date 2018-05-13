// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.utilities.cache.CacheKeyFilter;

// Referenced classes of package org.jvnet.hk2.internal:
//            CacheKey, ServiceLocatorImpl

class val.fAffectedContract
    implements CacheKeyFilter
{

            public boolean matches(CacheKey cachekey)
            {
/*1929*/        return CacheKey.access._mth1500(cachekey).matchesRemovalName(val$fAffectedContract);
            }

            public volatile boolean matches(Object obj)
            {
/*1926*/        return matches((CacheKey)obj);
            }

            final String val$fAffectedContract;
            final ServiceLocatorImpl this$0;

            CacheKey()
            {
/*1926*/        this$0 = final_servicelocatorimpl;
/*1926*/        val$fAffectedContract = String.this;
/*1926*/        super();
            }
}
