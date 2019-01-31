// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractThreadPoolProvider.java

package org.glassfish.jersey.spi;

import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import org.glassfish.jersey.internal.util.ExtendedLogger;

// Referenced classes of package org.glassfish.jersey.spi:
//            AbstractThreadPoolProvider

static class val.executorName
    implements PrivilegedAction
{

            public final Void run()
            {
                boolean flag;
                boolean flag1;
/* 352*/        if(!val$executorService.isShutdown())
/* 353*/            val$executorService.shutdown();
/* 355*/        if(val$executorService.isTerminated())
/* 356*/            return null;
/* 359*/        flag = false;
/* 360*/        flag1 = false;
/* 362*/        try
                {
/* 362*/            flag = val$executorService.awaitTermination(val$terminationTimeout, val$terminationTimeUnit);
                }
/* 363*/        catch(InterruptedException interruptedexception)
                {
/* 364*/            if(AbstractThreadPoolProvider.access$100().isDebugLoggable())
/* 365*/                AbstractThreadPoolProvider.access$100().log(AbstractThreadPoolProvider.access$100().getDebugLevel(), (new StringBuilder("Interrupted while waiting for thread pool executor ")).append(val$executorName).append(" to shutdown.").toString(), interruptedexception);
/* 368*/            flag1 = true;
                }
/* 372*/        if(!flag)
                {
                    List list;
/* 373*/            Iterator iterator = (list = val$executorService.shutdownNow()).iterator();
/* 374*/            do
                    {
/* 374*/                if(!iterator.hasNext())
/* 374*/                    break;
                        Runnable runnable;
/* 374*/                if((runnable = (Runnable)iterator.next()) instanceof Future)
/* 376*/                    ((Future)runnable).cancel(true);
                    } while(true);
/* 380*/            if(AbstractThreadPoolProvider.access$100().isDebugLoggable())
/* 381*/                AbstractThreadPoolProvider.access$100().debugLog("Thread pool executor {0} forced-shut down. List of cancelled tasks: {1}", new Object[] {
/* 381*/                    val$executorName, list
                        });
                }
/* 386*/        if(flag1)
/* 388*/            Thread.currentThread().interrupt();
/* 388*/        break MISSING_BLOCK_LABEL_231;
                Exception exception;
/* 386*/        exception;
/* 386*/        if(flag1)
/* 388*/            Thread.currentThread().interrupt();
/* 388*/        throw exception;
/* 391*/        return null;
            }

            public final volatile Object run()
            {
/* 348*/        return run();
            }

            final ExecutorService val$executorService;
            final int val$terminationTimeout;
            final TimeUnit val$terminationTimeUnit;
            final String val$executorName;

            (ExecutorService executorservice, int i, TimeUnit timeunit, String s)
            {
/* 348*/        val$executorService = executorservice;
/* 348*/        val$terminationTimeout = i;
/* 348*/        val$terminationTimeUnit = timeunit;
/* 348*/        val$executorName = s;
/* 348*/        super();
            }
}
