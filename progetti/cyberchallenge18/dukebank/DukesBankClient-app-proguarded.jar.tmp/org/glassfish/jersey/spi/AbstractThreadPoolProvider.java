// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractThreadPoolProvider.java

package org.glassfish.jersey.spi;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler;

public abstract class AbstractThreadPoolProvider
{

            protected AbstractThreadPoolProvider(String s)
            {
/* 113*/        name = s;
            }

            protected final ThreadPoolExecutor getExecutor()
            {
/* 130*/        if(isClosed())
/* 131*/            throw new IllegalStateException(LocalizationMessages.THREAD_POOL_EXECUTOR_PROVIDER_CLOSED());
/* 133*/        else
/* 133*/            return (ThreadPoolExecutor)lazyExecutorServiceProvider.get();
            }

            protected abstract ThreadPoolExecutor createExecutor(int i, ThreadFactory threadfactory, RejectedExecutionHandler rejectedexecutionhandler);

            protected int getTerminationTimeout()
            {
/* 179*/        return 5000;
            }

            protected int getCorePoolSize()
            {
/* 197*/        return Runtime.getRuntime().availableProcessors();
            }

            protected RejectedExecutionHandler getRejectedExecutionHandler()
            {
/* 214*/        return new RejectedExecutionHandler() {

                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadpoolexecutor)
                    {
                    }

                    final AbstractThreadPoolProvider this$0;

                    
                    {
/* 214*/                this$0 = AbstractThreadPoolProvider.this;
/* 214*/                super();
                    }
        };
            }

            protected ThreadFactory getBackingThreadFactory()
            {
/* 242*/        return null;
            }

            private ThreadFactory createThreadFactory()
            {
/* 246*/        ThreadFactoryBuilder threadfactorybuilder = (new ThreadFactoryBuilder()).setNameFormat((new StringBuilder()).append(name).append("-%d").toString()).setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler());
                ThreadFactory threadfactory;
/* 250*/        if((threadfactory = getBackingThreadFactory()) != null)
/* 252*/            threadfactorybuilder.setThreadFactory(threadfactory);
/* 255*/        return threadfactorybuilder.build();
            }

            public final boolean isClosed()
            {
/* 265*/        return closed.get();
            }

            protected void onClose()
            {
            }

            public final void close()
            {
/* 315*/        if(!closed.compareAndSet(false, true))
/* 316*/            return;
/* 320*/        onClose();
                Exception exception;
/* 322*/        if(lazyExecutorServiceProvider.isInitialized())
                {
/* 323*/            AccessController.doPrivileged(shutdownExecutor(name, (ExecutorService)lazyExecutorServiceProvider.get(), getTerminationTimeout(), TimeUnit.MILLISECONDS));
/* 323*/            return;
                } else
                {
/* 330*/            return;
                }
/* 322*/        exception;
/* 322*/        if(lazyExecutorServiceProvider.isInitialized())
/* 323*/            AccessController.doPrivileged(shutdownExecutor(name, (ExecutorService)lazyExecutorServiceProvider.get(), getTerminationTimeout(), TimeUnit.MILLISECONDS));
/* 323*/        throw exception;
            }

            private static PrivilegedAction shutdownExecutor(String s, ExecutorService executorservice, int i, TimeUnit timeunit)
            {
/* 348*/        return new PrivilegedAction(executorservice, i, timeunit, s) {

                    public final Void run()
                    {
                        boolean flag;
                        boolean flag1;
/* 352*/                if(!executorService.isShutdown())
/* 353*/                    executorService.shutdown();
/* 355*/                if(executorService.isTerminated())
/* 356*/                    return null;
/* 359*/                flag = false;
/* 360*/                flag1 = false;
/* 362*/                try
                        {
/* 362*/                    flag = executorService.awaitTermination(terminationTimeout, terminationTimeUnit);
                        }
/* 363*/                catch(InterruptedException interruptedexception)
                        {
/* 364*/                    if(AbstractThreadPoolProvider.LOGGER.isDebugLoggable())
/* 365*/                        AbstractThreadPoolProvider.LOGGER.log(AbstractThreadPoolProvider.LOGGER.getDebugLevel(), (new StringBuilder("Interrupted while waiting for thread pool executor ")).append(executorName).append(" to shutdown.").toString(), interruptedexception);
/* 368*/                    flag1 = true;
                        }
/* 372*/                if(!flag)
                        {
                            List list;
/* 373*/                    Iterator iterator = (list = executorService.shutdownNow()).iterator();
/* 374*/                    do
                            {
/* 374*/                        if(!iterator.hasNext())
/* 374*/                            break;
                                Runnable runnable;
/* 374*/                        if((runnable = (Runnable)iterator.next()) instanceof Future)
/* 376*/                            ((Future)runnable).cancel(true);
                            } while(true);
/* 380*/                    if(AbstractThreadPoolProvider.LOGGER.isDebugLoggable())
/* 381*/                        AbstractThreadPoolProvider.LOGGER.debugLog("Thread pool executor {0} forced-shut down. List of cancelled tasks: {1}", new Object[] {
/* 381*/                            executorName, list
                                });
                        }
/* 386*/                if(flag1)
/* 388*/                    Thread.currentThread().interrupt();
/* 388*/                break MISSING_BLOCK_LABEL_231;
                        Exception exception;
/* 386*/                exception;
/* 386*/                if(flag1)
/* 388*/                    Thread.currentThread().interrupt();
/* 388*/                throw exception;
/* 391*/                return null;
                    }

                    public final volatile Object run()
                    {
/* 348*/                return run();
                    }

                    final ExecutorService val$executorService;
                    final int val$terminationTimeout;
                    final TimeUnit val$terminationTimeUnit;
                    final String val$executorName;

                    
                    {
/* 348*/                executorService = executorservice;
/* 348*/                terminationTimeout = i;
/* 348*/                terminationTimeUnit = timeunit;
/* 348*/                executorName = s;
/* 348*/                super();
                    }
        };
            }

            private static final ExtendedLogger LOGGER;
            public static final int DEFAULT_TERMINATION_TIMEOUT = 5000;
            private final String name;
            private final AtomicBoolean closed = new AtomicBoolean(false);
            private final LazyValue lazyExecutorServiceProvider = Values.lazy(new Value() {

                public ThreadPoolExecutor get()
                {
/* 102*/            return createExecutor(getCorePoolSize(), createThreadFactory(), getRejectedExecutionHandler());
                }

                public volatile Object get()
                {
/*  98*/            return get();
                }

                final AbstractThreadPoolProvider this$0;

                    
                    {
/*  98*/                this$0 = AbstractThreadPoolProvider.this;
/*  98*/                super();
                    }
    });

            static 
            {
/*  87*/        LOGGER = new ExtendedLogger(Logger.getLogger(org/glassfish/jersey/spi/AbstractThreadPoolProvider.getName()), Level.FINEST);
            }


}
