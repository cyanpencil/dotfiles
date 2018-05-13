// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilder.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, CacheBuilder

static class tatsCounter
    implements Supplier
{

            public final tatsCounter get()
            {
/* 186*/        return new impleStatsCounter();
            }

            public final volatile Object get()
            {
/* 183*/        return get();
            }

            tatsCounter()
            {
            }
}
