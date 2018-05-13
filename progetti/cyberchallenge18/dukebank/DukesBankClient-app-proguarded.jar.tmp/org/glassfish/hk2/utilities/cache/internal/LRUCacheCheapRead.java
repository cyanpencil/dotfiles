// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUCacheCheapRead.java

package org.glassfish.hk2.utilities.cache.internal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.utilities.cache.*;

public class LRUCacheCheapRead extends LRUCache
{
    static class CacheEntryImpl
        implements CacheEntry
    {

                public void removeFromCache()
                {
/* 159*/            parent.cache.remove(key);
                }

                public CacheEntryImpl hit()
                {
/* 163*/            lastHit = System.nanoTime();
/* 164*/            return this;
                }

                final Object key;
                final Object value;
                final LRUCacheCheapRead parent;
                long lastHit;

                public CacheEntryImpl(Object obj, Object obj1, LRUCacheCheapRead lrucachecheapread)
                {
/* 151*/            parent = lrucachecheapread;
/* 152*/            key = obj;
/* 153*/            value = obj1;
/* 154*/            lastHit = System.nanoTime();
                }
    }

    static class CacheEntryImplComparator
        implements Comparator
    {

                public int compare(CacheEntryImpl cacheentryimpl, CacheEntryImpl cacheentryimpl1)
                {
                    long l;
/* 137*/            if((l = cacheentryimpl.lastHit - cacheentryimpl1.lastHit) > 0L)
/* 138*/                return 1;
/* 138*/            return l != 0L ? -1 : 0;
                }

                public volatile int compare(Object obj, Object obj1)
                {
/* 133*/            return compare((CacheEntryImpl)obj, (CacheEntryImpl)obj1);
                }

                private CacheEntryImplComparator()
                {
                }

    }


            public LRUCacheCheapRead(int i)
            {
/*  70*/        cache = new ConcurrentHashMap();
/*  78*/        maxCacheSize = i;
            }

            public Object get(Object obj)
            {
/*  83*/        if((obj = (CacheEntryImpl)cache.get(obj)) != null)
/*  84*/            return ((CacheEntryImpl) (obj)).hit().value;
/*  84*/        else
/*  84*/            return null;
            }

            public CacheEntry put(Object obj, Object obj1)
            {
/*  89*/        obj1 = new CacheEntryImpl(obj, obj1, this);
/*  90*/        Object obj2 = prunningLock;
/*  90*/        JVM INSTR monitorenter ;
/*  91*/        if(cache.size() + 1 > maxCacheSize)
/*  92*/            removeLRUItem();
/*  94*/        cache.put(obj, obj1);
/*  95*/        return ((CacheEntry) (obj1));
/*  96*/        obj;
/*  96*/        throw obj;
            }

            public void releaseCache()
            {
/* 101*/        cache.clear();
            }

            public int getMaxCacheSize()
            {
/* 106*/        return maxCacheSize;
            }

            public void releaseMatching(CacheKeyFilter cachekeyfilter)
            {
/* 111*/        if(cachekeyfilter == null)
/* 111*/            return;
/* 113*/        Iterator iterator = (new HashMap(cache)).entrySet().iterator();
/* 113*/        do
                {
/* 113*/            if(!iterator.hasNext())
/* 113*/                break;
/* 113*/            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
/* 114*/            if(cachekeyfilter.matches(entry.getKey()))
/* 115*/                ((CacheEntryImpl)entry.getValue()).removeFromCache();
                } while(true);
            }

            private void removeLRUItem()
            {
                java.util.Collection collection;
/* 127*/        ((CacheEntryImpl)Collections.min(collection = cache.values(), COMPARATOR)).removeFromCache();
            }

            final Object prunningLock = new Object();
            final int maxCacheSize;
            Map cache;
            private static final CacheEntryImplComparator COMPARATOR = new CacheEntryImplComparator();

}
