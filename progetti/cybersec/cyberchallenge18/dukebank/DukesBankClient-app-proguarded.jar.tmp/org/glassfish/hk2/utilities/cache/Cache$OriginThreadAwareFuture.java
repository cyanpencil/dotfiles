// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cache.java

package org.glassfish.hk2.utilities.cache;

import java.util.concurrent.*;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            Cache, Computable

class future
    implements Future
{

            public int hashCode()
            {
/* 108*/        return future.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 114*/        if(obj == null)
/* 115*/            return false;
/* 117*/        if(getClass() != obj.getClass())
/* 118*/            return false;
/* 121*/        obj = (future)obj;
/* 122*/        return future == ((future) (obj)).future || future != null && future.equals(((future) (obj)).future);
            }

            public boolean cancel(boolean flag)
            {
/* 130*/        return future.cancel(flag);
            }

            public boolean isCancelled()
            {
/* 135*/        return future.isCancelled();
            }

            public boolean isDone()
            {
/* 140*/        return future.isDone();
            }

            public Object get()
                throws InterruptedException, ExecutionException
            {
/* 145*/        return future.get();
            }

            public Object get(long l, TimeUnit timeunit)
                throws InterruptedException, ExecutionException, TimeoutException
            {
/* 150*/        return future.get(l, timeunit);
            }

            public void run()
            {
/* 154*/        future.run();
            }

            private volatile long threadId;
            private final FutureTask future;
            final Cache this$0;



            _cls1.val.key(Cache cache, final Object key)
            {
/*  91*/        this.this$0 = Cache.this;
/*  91*/        super();
/*  92*/        threadId = Thread.currentThread().getId();
/*  93*/        this$0 = new Callable() {

                    public Object call()
                        throws Exception
                    {
/*  97*/                Object obj = Cache.access$000(Cache.OriginThreadAwareFuture.this.this$0).compute(key);
/*  99*/                threadId = -1L;
/*  99*/                return obj;
                        Exception exception;
/*  99*/                exception;
/*  99*/                threadId = -1L;
/*  99*/                throw exception;
                    }

                    final Cache val$this$0;
                    final Object val$key;
                    final Cache.OriginThreadAwareFuture this$1;

                    
                    {
/*  93*/                this$1 = Cache.OriginThreadAwareFuture.this;
/*  93*/                this$0 = cache;
/*  93*/                key = obj;
/*  93*/                super();
                    }
        };
/* 103*/        future = new FutureTask(Cache.this);
            }
}
