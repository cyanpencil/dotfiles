// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            Callables, MoreExecutors

static class val.nameSupplier
    implements Executor
{

            public final void execute(Runnable runnable)
            {
/* 849*/        val$executor.execute(Callables.threadRenaming(runnable, val$nameSupplier));
            }

            final Executor val$executor;
            final Supplier val$nameSupplier;

            I(Executor executor1, Supplier supplier)
            {
/* 847*/        val$executor = executor1;
/* 847*/        val$nameSupplier = supplier;
/* 847*/        super();
            }
}
