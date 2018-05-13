// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AsyncSettableFuture, Futures, ListenableFuture

static class val.future
    implements Runnable
{

            public final void run()
            {
/*1199*/        ((AsyncSettableFuture)val$delegates.remove()).setFuture(val$future);
            }

            final ConcurrentLinkedQueue val$delegates;
            final ListenableFuture val$future;

            Future(ConcurrentLinkedQueue concurrentlinkedqueue, ListenableFuture listenablefuture)
            {
/*1197*/        val$delegates = concurrentlinkedqueue;
/*1197*/        val$future = listenablefuture;
/*1197*/        super();
            }
}
