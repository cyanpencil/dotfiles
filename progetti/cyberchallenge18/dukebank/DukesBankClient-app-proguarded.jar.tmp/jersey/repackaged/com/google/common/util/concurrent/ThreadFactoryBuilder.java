// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadFactoryBuilder.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import jersey.repackaged.com.google.common.base.Preconditions;

public final class ThreadFactoryBuilder
{

            public ThreadFactoryBuilder()
            {
/*  46*/        nameFormat = null;
/*  47*/        daemon = null;
/*  48*/        priority = null;
/*  49*/        uncaughtExceptionHandler = null;
/*  50*/        backingThreadFactory = null;
            }

            public final ThreadFactoryBuilder setNameFormat(String s)
            {
/*  71*/        String.format(s, new Object[] {
/*  71*/            Integer.valueOf(0)
                });
/*  72*/        nameFormat = s;
/*  73*/        return this;
            }

            public final ThreadFactoryBuilder setDaemon(boolean flag)
            {
/*  84*/        daemon = Boolean.valueOf(flag);
/*  85*/        return this;
            }

            public final ThreadFactoryBuilder setPriority(int i)
            {
/*  98*/        Preconditions.checkArgument(i > 0, "Thread priority (%s) must be >= %s", new Object[] {
/*  98*/            Integer.valueOf(i), Integer.valueOf(1)
                });
/* 100*/        Preconditions.checkArgument(i <= 10, "Thread priority (%s) must be <= %s", new Object[] {
/* 100*/            Integer.valueOf(i), Integer.valueOf(10)
                });
/* 102*/        priority = Integer.valueOf(i);
/* 103*/        return this;
            }

            public final ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
            {
/* 116*/        uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler)Preconditions.checkNotNull(uncaughtexceptionhandler);
/* 117*/        return this;
            }

            public final ThreadFactoryBuilder setThreadFactory(ThreadFactory threadfactory)
            {
/* 133*/        backingThreadFactory = (ThreadFactory)Preconditions.checkNotNull(threadfactory);
/* 134*/        return this;
            }

            public final ThreadFactory build()
            {
/* 146*/        return build(this);
            }

            private static ThreadFactory build(ThreadFactoryBuilder threadfactorybuilder)
            {
/* 150*/        String s = threadfactorybuilder.nameFormat;
/* 151*/        Boolean boolean1 = threadfactorybuilder.daemon;
/* 152*/        Integer integer = threadfactorybuilder.priority;
/* 153*/        Thread.UncaughtExceptionHandler uncaughtexceptionhandler = threadfactorybuilder.uncaughtExceptionHandler;
/* 155*/        threadfactorybuilder = threadfactorybuilder.backingThreadFactory == null ? ((ThreadFactoryBuilder) (Executors.defaultThreadFactory())) : ((ThreadFactoryBuilder) (threadfactorybuilder.backingThreadFactory));
/* 159*/        AtomicLong atomiclong = s == null ? null : new AtomicLong(0L);
/* 160*/        return new ThreadFactory(threadfactorybuilder, s, atomiclong, boolean1, integer, uncaughtexceptionhandler) {

                    public final Thread newThread(Runnable runnable)
                    {
/* 162*/                runnable = backingThreadFactory.newThread(runnable);
/* 163*/                if(nameFormat != null)
/* 164*/                    runnable.setName(String.format(nameFormat, new Object[] {
/* 164*/                        Long.valueOf(count.getAndIncrement())
                            }));
/* 166*/                if(daemon != null)
/* 167*/                    runnable.setDaemon(daemon.booleanValue());
/* 169*/                if(priority != null)
/* 170*/                    runnable.setPriority(priority.intValue());
/* 172*/                if(uncaughtExceptionHandler != null)
/* 173*/                    runnable.setUncaughtExceptionHandler(uncaughtExceptionHandler);
/* 175*/                return runnable;
                    }

                    final ThreadFactory val$backingThreadFactory;
                    final String val$nameFormat;
                    final AtomicLong val$count;
                    final Boolean val$daemon;
                    final Integer val$priority;
                    final Thread.UncaughtExceptionHandler val$uncaughtExceptionHandler;

                    
                    {
/* 160*/                backingThreadFactory = threadfactory;
/* 160*/                nameFormat = s;
/* 160*/                count = atomiclong;
/* 160*/                daemon = boolean1;
/* 160*/                priority = integer;
/* 160*/                uncaughtExceptionHandler = uncaughtexceptionhandler;
/* 160*/                super();
                    }
        };
            }

            private String nameFormat;
            private Boolean daemon;
            private Integer priority;
            private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
            private ThreadFactory backingThreadFactory;
}
