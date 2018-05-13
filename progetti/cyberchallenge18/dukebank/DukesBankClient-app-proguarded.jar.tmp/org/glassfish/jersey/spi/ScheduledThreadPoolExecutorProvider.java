// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScheduledThreadPoolExecutorProvider.java

package org.glassfish.jersey.spi;

import java.util.concurrent.*;

// Referenced classes of package org.glassfish.jersey.spi:
//            AbstractThreadPoolProvider, ScheduledExecutorServiceProvider

public class ScheduledThreadPoolExecutorProvider extends AbstractThreadPoolProvider
    implements ScheduledExecutorServiceProvider
{

            public ScheduledThreadPoolExecutorProvider(String s)
            {
/*  89*/        super(s);
            }

            public ScheduledExecutorService getExecutorService()
            {
/*  94*/        return (ScheduledExecutorService)super.getExecutor();
            }

            protected ScheduledThreadPoolExecutor createExecutor(int i, ThreadFactory threadfactory, RejectedExecutionHandler rejectedexecutionhandler)
            {
/* 100*/        return new ScheduledThreadPoolExecutor(i, threadfactory, rejectedexecutionhandler);
            }

            public void dispose(ExecutorService executorservice)
            {
            }

            public void preDestroy()
            {
/* 116*/        close();
            }

            protected volatile ThreadPoolExecutor createExecutor(int i, ThreadFactory threadfactory, RejectedExecutionHandler rejectedexecutionhandler)
            {
/*  79*/        return createExecutor(i, threadfactory, rejectedexecutionhandler);
            }

            public volatile ExecutorService getExecutorService()
            {
/*  79*/        return getExecutorService();
            }
}
