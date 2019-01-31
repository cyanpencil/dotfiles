// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cache.java

package org.glassfish.hk2.utilities.cache;

import java.util.concurrent.*;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            Computable

public class Cache
    implements Computable
{
    class OriginThreadAwareFuture
        implements Future
    {

                public int hashCode()
                {
/* 108*/            return future.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 114*/            if(obj == null)
/* 115*/                return false;
/* 117*/            if(getClass() != obj.getClass())
/* 118*/                return false;
/* 121*/            obj = (OriginThreadAwareFuture)obj;
/* 122*/            return future == ((OriginThreadAwareFuture) (obj)).future || future != null && future.equals(((OriginThreadAwareFuture) (obj)).future);
                }

                public boolean cancel(boolean flag)
                {
/* 130*/            return future.cancel(flag);
                }

                public boolean isCancelled()
                {
/* 135*/            return future.isCancelled();
                }

                public boolean isDone()
                {
/* 140*/            return future.isDone();
                }

                public Object get()
                    throws InterruptedException, ExecutionException
                {
/* 145*/            return future.get();
                }

                public Object get(long l, TimeUnit timeunit)
                    throws InterruptedException, ExecutionException, TimeoutException
                {
/* 150*/            return future.get(l, timeunit);
                }

                public void run()
                {
/* 154*/            future.run();
                }

                private volatile long threadId;
                private final FutureTask future;
                final Cache this$0;



                OriginThreadAwareFuture(Cache cache2, final Object key)
                {
/*  91*/            this$0 = Cache.this;
/*  91*/            super();
/*  92*/            threadId = Thread.currentThread().getId();
/*  93*/            cache1 = new Callable() {

                        public Object call()
                            throws Exception
                        {
/*  97*/                    Object obj = computable.compute(key);
/*  99*/                    threadId = -1L;
/*  99*/                    return obj;
                            Exception exception;
/*  99*/                    exception;
/*  99*/                    threadId = -1L;
/*  99*/                    throw exception;
                        }

                        final Cache val$this$0;
                        final Object val$key;
                        final OriginThreadAwareFuture this$1;


// JavaClassFileOutputException: Invalid index accessing method local variables table of <init>
            };
/* 103*/            future = new FutureTask(Cache.this);
                }
    }

    public static interface CycleHandler
    {

        public abstract void handleCycle(Object obj);
    }


            public Cache(Computable computable1)
            {
/* 175*/        this(computable1, EMPTY_HANDLER);
            }

            public Cache(Computable computable1, CycleHandler cyclehandler)
            {
/* 164*/        cache = new ConcurrentHashMap();
/* 185*/        computable = computable1;
/* 186*/        cycleHandler = cyclehandler;
            }

            public Object compute(Object obj)
            {
                OriginThreadAwareFuture originthreadawarefuture;
                long l;
/* 192*/        if((originthreadawarefuture = (OriginThreadAwareFuture)cache.get(obj)) == null)
                {
/* 194*/            OriginThreadAwareFuture originthreadawarefuture1 = new OriginThreadAwareFuture(this, obj);
/* 196*/            if((originthreadawarefuture = (OriginThreadAwareFuture)cache.putIfAbsent(obj, originthreadawarefuture1)) == null)
                    {
/* 198*/                originthreadawarefuture = originthreadawarefuture1;
/* 199*/                originthreadawarefuture1.run();
                    }
                } else
/* 202*/        if((l = originthreadawarefuture.threadId) != -1L && Thread.currentThread().getId() == originthreadawarefuture.threadId)
/* 205*/            cycleHandler.handleCycle(obj);
/* 209*/        return originthreadawarefuture.get();
                Object obj1;
/* 210*/        obj1;
/* 211*/        throw new RuntimeException(((Throwable) (obj1)));
/* 212*/        obj1;
/* 213*/        cache.remove(obj);
                Throwable throwable;
/* 214*/        if((throwable = ((ExecutionException) (obj1)).getCause()) == null)
/* 216*/            throw new RuntimeException(((Throwable) (obj1)));
/* 219*/        if(throwable instanceof RuntimeException)
/* 220*/            throw (RuntimeException)throwable;
/* 223*/        else
/* 223*/            throw new RuntimeException(throwable);
            }

            public void clear()
            {
/* 232*/        cache.clear();
            }

            public boolean containsKey(Object obj)
            {
/* 242*/        return cache.containsKey(obj);
            }

            public void remove(Object obj)
            {
/* 251*/        cache.remove(obj);
            }

            public int size()
            {
/* 259*/        return cache.size();
            }

            private final CycleHandler cycleHandler;
            private static final CycleHandler EMPTY_HANDLER = new CycleHandler() {

                public final void handleCycle(Object obj)
                {
                }

    };
            private final ConcurrentHashMap cache;
            private final Computable computable;


}
