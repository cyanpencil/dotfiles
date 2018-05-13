// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WrappingScheduledExecutorService.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            WrappingExecutorService

abstract class WrappingScheduledExecutorService extends WrappingExecutorService
    implements ScheduledExecutorService
{

            protected WrappingScheduledExecutorService(ScheduledExecutorService scheduledexecutorservice)
            {
/*  36*/        super(scheduledexecutorservice);
/*  37*/        _flddelegate = scheduledexecutorservice;
            }

            public final ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
            {
/*  42*/        return _flddelegate.schedule(wrapTask(runnable), l, timeunit);
            }

            public final ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
            {
/*  47*/        return _flddelegate.schedule(wrapTask(callable), l, timeunit);
            }

            public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
            {
/*  53*/        return _flddelegate.scheduleAtFixedRate(wrapTask(runnable), l, l1, timeunit);
            }

            public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
            {
/*  59*/        return _flddelegate.scheduleWithFixedDelay(wrapTask(runnable), l, l1, timeunit);
            }

            final ScheduledExecutorService _flddelegate;
}
