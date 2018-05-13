// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            WrappingExecutorService, Callables, MoreExecutors

static class Service extends WrappingExecutorService
{

            protected final Callable wrapTask(Callable callable)
            {
/* 876*/        return Callables.threadRenaming(callable, val$nameSupplier);
            }

            protected final Runnable wrapTask(Runnable runnable)
            {
/* 879*/        return Callables.threadRenaming(runnable, val$nameSupplier);
            }

            final Supplier val$nameSupplier;

            Service(ExecutorService executorservice, Supplier supplier)
            {
/* 874*/        val$nameSupplier = supplier;
/* 874*/        super(executorservice);
            }
}
