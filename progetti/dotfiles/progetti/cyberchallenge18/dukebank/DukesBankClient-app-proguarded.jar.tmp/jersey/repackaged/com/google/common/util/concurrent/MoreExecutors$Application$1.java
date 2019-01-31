// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            MoreExecutors

class val.timeUnit
    implements Runnable
{

            public void run()
            {
/* 204*/        try
                {
/* 204*/            val$service.shutdown();
/* 205*/            val$service.awaitTermination(val$terminationTimeout, val$timeUnit);
/* 208*/            return;
                }
/* 206*/        catch(InterruptedException _ex)
                {
/* 209*/            return;
                }
            }

            final ExecutorService val$service;
            final long val$terminationTimeout;
            final TimeUnit val$timeUnit;
            final val.timeUnit this$0;

            ()
            {
/* 195*/        this$0 = final_;
/* 195*/        val$service = executorservice;
/* 195*/        val$terminationTimeout = l;
/* 195*/        val$timeUnit = TimeUnit.this;
/* 195*/        super();
            }
}
