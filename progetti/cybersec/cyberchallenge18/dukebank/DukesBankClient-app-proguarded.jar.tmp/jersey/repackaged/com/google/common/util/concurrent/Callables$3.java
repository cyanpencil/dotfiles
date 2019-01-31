// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Callables.java

package jersey.repackaged.com.google.common.util.concurrent;

import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            Callables

static class val.task
    implements Runnable
{

            public final void run()
            {
                Thread thread;
                String s;
                boolean flag;
/*  91*/        s = (thread = Thread.currentThread()).getName();
/*  93*/        flag = Callables.access$000((String)val$nameSupplier.get(), thread);
/*  95*/        val$task.run();
                Exception exception;
/*  97*/        if(flag)
                {
/*  98*/            Callables.access$000(s, thread);
/*  98*/            return;
                } else
                {
/* 101*/            return;
                }
/*  97*/        exception;
/*  97*/        if(flag)
/*  98*/            Callables.access$000(s, thread);
/*  98*/        throw exception;
            }

            final Supplier val$nameSupplier;
            final Runnable val$task;

            (Supplier supplier, Runnable runnable)
            {
/*  89*/        val$nameSupplier = supplier;
/*  89*/        val$task = runnable;
/*  89*/        super();
            }
}
