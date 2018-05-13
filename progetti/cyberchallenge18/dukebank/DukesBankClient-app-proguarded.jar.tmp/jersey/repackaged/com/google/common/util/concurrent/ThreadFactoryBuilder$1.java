// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadFactoryBuilder.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ThreadFactoryBuilder

static class val.uncaughtExceptionHandler
    implements ThreadFactory
{

            public final Thread newThread(Runnable runnable)
            {
/* 162*/        runnable = val$backingThreadFactory.newThread(runnable);
/* 163*/        if(val$nameFormat != null)
/* 164*/            runnable.setName(String.format(val$nameFormat, new Object[] {
/* 164*/                Long.valueOf(val$count.getAndIncrement())
                    }));
/* 166*/        if(val$daemon != null)
/* 167*/            runnable.setDaemon(val$daemon.booleanValue());
/* 169*/        if(val$priority != null)
/* 170*/            runnable.setPriority(val$priority.intValue());
/* 172*/        if(val$uncaughtExceptionHandler != null)
/* 173*/            runnable.setUncaughtExceptionHandler(val$uncaughtExceptionHandler);
/* 175*/        return runnable;
            }

            final ThreadFactory val$backingThreadFactory;
            final String val$nameFormat;
            final AtomicLong val$count;
            final Boolean val$daemon;
            final Integer val$priority;
            final nHandler val$uncaughtExceptionHandler;

            (ThreadFactory threadfactory, String s, AtomicLong atomiclong, Boolean boolean1, Integer integer, nHandler nhandler)
            {
/* 160*/        val$backingThreadFactory = threadfactory;
/* 160*/        val$nameFormat = s;
/* 160*/        val$count = atomiclong;
/* 160*/        val$daemon = boolean1;
/* 160*/        val$priority = integer;
/* 160*/        val$uncaughtExceptionHandler = nhandler;
/* 160*/        super();
            }
}
