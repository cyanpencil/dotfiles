// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractListeningExecutorService.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ListenableFuture, ListenableFutureTask, ListeningExecutorService

public abstract class AbstractListeningExecutorService extends AbstractExecutorService
    implements ListeningExecutorService
{

            public AbstractListeningExecutorService()
            {
            }

            protected final ListenableFutureTask newTaskFor(Runnable runnable, Object obj)
            {
/*  42*/        return ListenableFutureTask.create(runnable, obj);
            }

            protected final ListenableFutureTask newTaskFor(Callable callable)
            {
/*  46*/        return ListenableFutureTask.create(callable);
            }

            public ListenableFuture submit(Runnable runnable)
            {
/*  50*/        return (ListenableFuture)super.submit(runnable);
            }

            public ListenableFuture submit(Runnable runnable, Object obj)
            {
/*  54*/        return (ListenableFuture)super.submit(runnable, obj);
            }

            public ListenableFuture submit(Callable callable)
            {
/*  58*/        return (ListenableFuture)super.submit(callable);
            }

            public volatile Future submit(Callable callable)
            {
/*  37*/        return submit(callable);
            }

            public volatile Future submit(Runnable runnable, Object obj)
            {
/*  37*/        return submit(runnable, obj);
            }

            public volatile Future submit(Runnable runnable)
            {
/*  37*/        return submit(runnable);
            }

            protected volatile RunnableFuture newTaskFor(Callable callable)
            {
/*  37*/        return newTaskFor(callable);
            }

            protected volatile RunnableFuture newTaskFor(Runnable runnable, Object obj)
            {
/*  37*/        return newTaskFor(runnable, obj);
            }
}
