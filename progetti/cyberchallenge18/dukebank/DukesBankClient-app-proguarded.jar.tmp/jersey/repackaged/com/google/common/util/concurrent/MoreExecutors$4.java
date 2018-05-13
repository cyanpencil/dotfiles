// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            WrappingScheduledExecutorService, Callables, MoreExecutors

static class dExecutorService extends WrappingScheduledExecutorService
{

            protected final Callable wrapTask(Callable callable)
            {
/* 906*/        return Callables.threadRenaming(callable, val$nameSupplier);
            }

            protected final Runnable wrapTask(Runnable runnable)
            {
/* 909*/        return Callables.threadRenaming(runnable, val$nameSupplier);
            }

            final Supplier val$nameSupplier;

            dExecutorService(ScheduledExecutorService scheduledexecutorservice, Supplier supplier)
            {
/* 904*/        val$nameSupplier = supplier;
/* 904*/        super(scheduledexecutorservice);
            }
}
