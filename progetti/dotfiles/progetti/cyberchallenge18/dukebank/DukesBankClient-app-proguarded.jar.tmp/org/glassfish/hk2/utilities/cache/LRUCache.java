// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUCache.java

package org.glassfish.hk2.utilities.cache;

import org.glassfish.hk2.utilities.cache.internal.LRUCacheCheapRead;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            CacheEntry, CacheKeyFilter

public abstract class LRUCache
{

            public LRUCache()
            {
            }

            public static LRUCache createCache(int i)
            {
/*  62*/        return new LRUCacheCheapRead(i);
            }

            public abstract Object get(Object obj);

            public abstract CacheEntry put(Object obj, Object obj1);

            public abstract void releaseCache();

            public abstract int getMaxCacheSize();

            public abstract void releaseMatching(CacheKeyFilter cachekeyfilter);
}
