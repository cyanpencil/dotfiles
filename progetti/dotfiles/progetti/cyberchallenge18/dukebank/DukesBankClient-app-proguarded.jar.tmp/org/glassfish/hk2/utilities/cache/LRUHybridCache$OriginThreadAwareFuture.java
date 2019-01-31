// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUHybridCache.java

package org.glassfish.hk2.utilities.cache;

import java.util.concurrent.*;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            HybridCacheEntry, LRUHybridCache, Computable

class lastHit
    implements Future
{

            public int hashCode()
            {
/* 128*/        return future.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 134*/        if(obj == null)
/* 135*/            return false;
/* 137*/        if(getClass() != obj.getClass())
/* 138*/            return false;
/* 140*/        obj = (future)obj;
/* 141*/        return future == ((future) (obj)).future || future != null && future.equals(((future) (obj)).future);
            }

            public boolean cancel(boolean flag)
            {
/* 149*/        return future.cancel(flag);
            }

            public boolean isCancelled()
            {
/* 154*/        return future.isCancelled();
            }

            public boolean isDone()
            {
/* 159*/        return future.isDone();
            }

            public HybridCacheEntry get()
                throws InterruptedException, ExecutionException
            {
/* 164*/        return (HybridCacheEntry)future.get();
            }

            public HybridCacheEntry get(long l, TimeUnit timeunit)
                throws InterruptedException, ExecutionException, TimeoutException
            {
/* 169*/        return (HybridCacheEntry)future.get(l, timeunit);
            }

            public void run()
            {
/* 173*/        future.run();
            }

            public volatile Object get(long l, TimeUnit timeunit)
                throws InterruptedException, ExecutionException, TimeoutException
            {
/* 102*/        return get(l, timeunit);
            }

            public volatile Object get()
                throws InterruptedException, ExecutionException
            {
/* 102*/        return get();
            }

            private final Object key;
            private final FutureTask future;
            private volatile long threadId;
            private volatile long lastHit;
            final LRUHybridCache this$0;






            _cls1.val.key(LRUHybridCache lruhybridcache, final Object key)
            {
/* 108*/        this.this$0 = LRUHybridCache.this;
/* 108*/        super();
/* 109*/        this.key = key;
/* 110*/        threadId = Thread.currentThread().getId();
/* 111*/        this$0 = new Callable() {

                    public HybridCacheEntry call()
                        throws Exception
                    {
                        HybridCacheEntry hybridcacheentry;
/* 115*/                hybridcacheentry = hybridcacheentry = (HybridCacheEntry)LRUHybridCache.access$000(LRUHybridCache.OriginThreadAwareFuture.this.this$0).compute(key);
/* 118*/                threadId = -1L;
/* 118*/                return hybridcacheentry;
                        Exception exception;
/* 118*/                exception;
/* 118*/                threadId = -1L;
/* 118*/                throw exception;
                    }

                    public volatile Object call()
                        throws Exception
                    {
/* 111*/                return call();
                    }

                    final LRUHybridCache val$this$0;
                    final Object val$key;
                    final LRUHybridCache.OriginThreadAwareFuture this$1;

                    
                    {
/* 111*/                this$1 = LRUHybridCache.OriginThreadAwareFuture.this;
/* 111*/                this$0 = lruhybridcache;
/* 111*/                key = obj;
/* 111*/                super();
                    }
        };
/* 122*/        future = new FutureTask(LRUHybridCache.this);
/* 123*/        lastHit = System.nanoTime();
            }
}
