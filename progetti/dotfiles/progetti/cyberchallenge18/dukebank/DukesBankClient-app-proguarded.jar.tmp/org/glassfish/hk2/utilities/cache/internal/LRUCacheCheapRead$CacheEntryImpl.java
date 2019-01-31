// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUCacheCheapRead.java

package org.glassfish.hk2.utilities.cache.internal;

import java.util.Map;
import org.glassfish.hk2.utilities.cache.CacheEntry;

// Referenced classes of package org.glassfish.hk2.utilities.cache.internal:
//            LRUCacheCheapRead

static class lastHit
    implements CacheEntry
{

            public void removeFromCache()
            {
/* 159*/        parent.cache.remove(key);
            }

            public key hit()
            {
/* 163*/        lastHit = System.nanoTime();
/* 164*/        return this;
            }

            final Object key;
            final Object value;
            final LRUCacheCheapRead parent;
            long lastHit;

            public Q(Object obj, Object obj1, LRUCacheCheapRead lrucachecheapread)
            {
/* 151*/        parent = lrucachecheapread;
/* 152*/        key = obj;
/* 153*/        value = obj1;
/* 154*/        lastHit = System.nanoTime();
            }
}
