// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUHybridCache.java

package org.glassfish.hk2.utilities.cache;

import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            CacheKeyFilter, Computable, ComputationErrorException, HybridCacheEntry

public class LRUHybridCache
    implements Computable
{
    static class CacheEntryImplComparator
        implements Comparator
    {

                public int compare(OriginThreadAwareFuture originthreadawarefuture, OriginThreadAwareFuture originthreadawarefuture1)
                {
                    long l;
/* 375*/            if((l = originthreadawarefuture.lastHit - originthreadawarefuture1.lastHit) > 0L)
/* 376*/                return 1;
/* 376*/            return l != 0L ? -1 : 0;
                }

                public volatile int compare(Object obj, Object obj1)
                {
/* 371*/            return compare((OriginThreadAwareFuture)obj, (OriginThreadAwareFuture)obj1);
                }

                private CacheEntryImplComparator()
                {
                }

    }

    final class HybridCacheEntryImpl
        implements HybridCacheEntry
    {

                public final Object getValue()
                {
/* 226*/            return value;
                }

                public final boolean dropMe()
                {
/* 231*/            return dropMe;
                }

                public final void removeFromCache()
                {
/* 236*/            remove(key);
                }

                public final int hashCode()
                {
                    int i;
/* 242*/            return i = 115 + (key == null ? 0 : key.hashCode());
                }

                public final boolean equals(Object obj)
                {
/* 249*/            if(obj == null)
/* 250*/                return false;
/* 252*/            if(getClass() != obj.getClass())
/* 253*/                return false;
/* 255*/            obj = (HybridCacheEntryImpl)obj;
/* 256*/            return key == ((HybridCacheEntryImpl) (obj)).key || key != null && key.equals(((HybridCacheEntryImpl) (obj)).key);
                }

                private final Object key;
                private final Object value;
                private final boolean dropMe;
                final LRUHybridCache this$0;

                public HybridCacheEntryImpl(Object obj, Object obj1, boolean flag)
                {
/* 218*/            this$0 = LRUHybridCache.this;
/* 218*/            super();
/* 219*/            key = obj;
/* 220*/            value = obj1;
/* 221*/            dropMe = flag;
                }
    }

    class OriginThreadAwareFuture
        implements Future
    {

                public int hashCode()
                {
/* 128*/            return future.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 134*/            if(obj == null)
/* 135*/                return false;
/* 137*/            if(getClass() != obj.getClass())
/* 138*/                return false;
/* 140*/            obj = (OriginThreadAwareFuture)obj;
/* 141*/            return future == ((OriginThreadAwareFuture) (obj)).future || future != null && future.equals(((OriginThreadAwareFuture) (obj)).future);
                }

                public boolean cancel(boolean flag)
                {
/* 149*/            return future.cancel(flag);
                }

                public boolean isCancelled()
                {
/* 154*/            return future.isCancelled();
                }

                public boolean isDone()
                {
/* 159*/            return future.isDone();
                }

                public HybridCacheEntry get()
                    throws InterruptedException, ExecutionException
                {
/* 164*/            return (HybridCacheEntry)future.get();
                }

                public HybridCacheEntry get(long l, TimeUnit timeunit)
                    throws InterruptedException, ExecutionException, TimeoutException
                {
/* 169*/            return (HybridCacheEntry)future.get(l, timeunit);
                }

                public void run()
                {
/* 173*/            future.run();
                }

                public volatile Object get(long l, TimeUnit timeunit)
                    throws InterruptedException, ExecutionException, TimeoutException
                {
/* 102*/            return get(l, timeunit);
                }

                public volatile Object get()
                    throws InterruptedException, ExecutionException
                {
/* 102*/            return get();
                }

                private final Object key;
                private final FutureTask future;
                private volatile long threadId;
                private volatile long lastHit;
                final LRUHybridCache this$0;






                OriginThreadAwareFuture(LRUHybridCache lruhybridcache1, final Object key)
                {
/* 108*/            this$0 = LRUHybridCache.this;
/* 108*/            super();
/* 109*/            this.key = key;
/* 110*/            threadId = Thread.currentThread().getId();
/* 111*/            lruhybridcache = new Callable() {

                        public HybridCacheEntry call()
                            throws Exception
                        {
                            HybridCacheEntry hybridcacheentry;
/* 115*/                    hybridcacheentry = hybridcacheentry = (HybridCacheEntry)computable.compute(key);
/* 118*/                    threadId = -1L;
/* 118*/                    return hybridcacheentry;
                            Exception exception;
/* 118*/                    exception;
/* 118*/                    threadId = -1L;
/* 118*/                    throw exception;
                        }

                        public volatile Object call()
                            throws Exception
                        {
/* 111*/                    return call();
                        }

                        final LRUHybridCache val$this$0;
                        final Object val$key;
                        final OriginThreadAwareFuture this$1;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            };
/* 122*/            future = new FutureTask(LRUHybridCache.this);
/* 123*/            lastHit = System.nanoTime();
                }
    }

    public static interface CycleHandler
    {

        public abstract void handleCycle(Object obj);
    }


            public LRUHybridCache(int i, Computable computable1)
            {
/* 196*/        this(i, computable1, EMPTY_CYCLE_HANDLER);
            }

            public LRUHybridCache(int i, Computable computable1, CycleHandler cyclehandler)
            {
/* 183*/        cache = new ConcurrentHashMap();
/* 186*/        prunningLock = new Object();
/* 207*/        maxCacheSize = i;
/* 208*/        computable = computable1;
/* 209*/        cycleHandler = cyclehandler;
            }

            public HybridCacheEntry createCacheEntry(Object obj, Object obj1, boolean flag)
            {
/* 272*/        return new HybridCacheEntryImpl(obj, obj1, flag);
            }

            public HybridCacheEntry compute(Object obj)
            {
                OriginThreadAwareFuture originthreadawarefuture;
/* 278*/        if((originthreadawarefuture = (OriginThreadAwareFuture)cache.get(obj)) == null)
                {
/* 281*/            OriginThreadAwareFuture originthreadawarefuture1 = new OriginThreadAwareFuture(this, obj);
/* 284*/            synchronized(prunningLock)
                    {
/* 285*/                if(cache.size() + 1 > maxCacheSize)
/* 286*/                    removeLRUItem();
/* 288*/                originthreadawarefuture = (OriginThreadAwareFuture)cache.putIfAbsent(obj, originthreadawarefuture1);
                    }
/* 290*/            if(originthreadawarefuture == null)
                    {
/* 291*/                originthreadawarefuture = originthreadawarefuture1;
/* 292*/                originthreadawarefuture1.run();
                    }
                } else
                {
                    long l;
/* 295*/            if((l = originthreadawarefuture.threadId) != -1L && Thread.currentThread().getId() == originthreadawarefuture.threadId)
/* 298*/                cycleHandler.handleCycle(obj);
/* 300*/            originthreadawarefuture.lastHit = System.nanoTime();
                }
                HybridCacheEntry hybridcacheentry;
/* 303*/        if((hybridcacheentry = originthreadawarefuture.get()).dropMe())
/* 305*/            cache.remove(obj);
/* 307*/        return hybridcacheentry;
                Object obj1;
/* 308*/        obj1;
/* 309*/        throw new RuntimeException(((Throwable) (obj1)));
/* 310*/        obj1;
/* 311*/        cache.remove(obj);
/* 312*/        if(((ExecutionException) (obj1)).getCause() instanceof RuntimeException)
/* 313*/            throw (RuntimeException)((ExecutionException) (obj1)).getCause();
/* 315*/        else
/* 315*/            throw new RuntimeException(((Throwable) (obj1)));
            }

            public void clear()
            {
/* 325*/        cache.clear();
            }

            public int size()
            {
/* 333*/        return cache.size();
            }

            public int getMaximumCacheSize()
            {
/* 337*/        return maxCacheSize;
            }

            public boolean containsKey(Object obj)
            {
/* 347*/        return cache.containsKey(obj);
            }

            public void remove(Object obj)
            {
/* 356*/        cache.remove(obj);
            }

            private void removeLRUItem()
            {
/* 365*/        java.util.Collection collection = cache.values();
/* 366*/        cache.remove(((OriginThreadAwareFuture)Collections.min(collection, COMPARATOR)).key);
            }

            public void releaseMatching(CacheKeyFilter cachekeyfilter)
            {
/* 389*/        if(cachekeyfilter == null)
/* 389*/            return;
/* 390*/        Iterator iterator = cache.keySet().iterator();
/* 390*/        do
                {
/* 390*/            if(!iterator.hasNext())
/* 390*/                break;
/* 390*/            Object obj = iterator.next();
/* 391*/            if(cachekeyfilter.matches(obj))
/* 392*/                cache.remove(obj);
                } while(true);
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*  71*/        return compute(obj);
            }

            private final CycleHandler cycleHandler;
            private static final CycleHandler EMPTY_CYCLE_HANDLER = new CycleHandler() {

                public final void handleCycle(Object obj)
                {
                }

    };
            private final ConcurrentHashMap cache;
            private final Computable computable;
            private final Object prunningLock;
            private final int maxCacheSize;
            private static final Comparator COMPARATOR = new CacheEntryImplComparator();


}
