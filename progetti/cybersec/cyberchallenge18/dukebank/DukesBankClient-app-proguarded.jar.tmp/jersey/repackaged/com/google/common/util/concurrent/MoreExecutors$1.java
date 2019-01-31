// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.BlockingQueue;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            MoreExecutors, ListenableFuture

static class val.future
    implements Runnable
{

            public final void run()
            {
/* 751*/        val$queue.add(val$future);
            }

            final BlockingQueue val$queue;
            final ListenableFuture val$future;

            (BlockingQueue blockingqueue, ListenableFuture listenablefuture)
            {
/* 749*/        val$queue = blockingqueue;
/* 749*/        val$future = listenablefuture;
/* 749*/        super();
            }
}
