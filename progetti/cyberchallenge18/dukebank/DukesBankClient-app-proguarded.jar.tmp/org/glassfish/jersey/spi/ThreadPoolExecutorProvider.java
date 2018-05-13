// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadPoolExecutorProvider.java

package org.glassfish.jersey.spi;

import java.util.AbstractQueue;
import java.util.concurrent.*;

// Referenced classes of package org.glassfish.jersey.spi:
//            AbstractThreadPoolProvider, ExecutorServiceProvider

public class ThreadPoolExecutorProvider extends AbstractThreadPoolProvider
    implements ExecutorServiceProvider
{

            public ThreadPoolExecutorProvider(String s)
            {
/*  84*/        super(s);
            }

            public ExecutorService getExecutorService()
            {
/*  89*/        return super.getExecutor();
            }

            protected final ThreadPoolExecutor createExecutor(int i, ThreadFactory threadfactory, RejectedExecutionHandler rejectedexecutionhandler)
            {
/*  95*/        return createExecutor(i, getMaximumPoolSize(), getKeepAliveTime(), getWorkQueue(), threadfactory, rejectedexecutionhandler);
            }

            protected ThreadPoolExecutor createExecutor(int i, int j, long l, BlockingQueue blockingqueue, ThreadFactory threadfactory, RejectedExecutionHandler rejectedexecutionhandler)
            {
/* 132*/        return new ThreadPoolExecutor(i, j, l, TimeUnit.SECONDS, blockingqueue, threadfactory, rejectedexecutionhandler);
            }

            protected int getMaximumPoolSize()
            {
/* 157*/        return 0x7fffffff;
            }

            protected long getKeepAliveTime()
            {
/* 188*/        return 60L;
            }

            protected BlockingQueue getWorkQueue()
            {
/* 219*/        return (BlockingQueue)(getMaximumPoolSize() != 0x7fffffff ? new LinkedBlockingQueue() : new SynchronousQueue());
            }

            public void dispose(ExecutorService executorservice)
            {
            }

            public void preDestroy()
            {
/* 236*/        close();
            }

            private static final long CACHED_POOL_KEEP_ALIVE_DEFAULT_TIMEOUT = 60L;
}
