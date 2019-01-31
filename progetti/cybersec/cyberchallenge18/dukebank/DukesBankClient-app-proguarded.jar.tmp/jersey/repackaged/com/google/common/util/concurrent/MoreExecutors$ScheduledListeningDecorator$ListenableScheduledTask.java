// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ForwardingListenableFuture, ListenableScheduledFuture, MoreExecutors, ListenableFuture

static final class scheduledDelegate extends scheduledDelegate
    implements ListenableScheduledFuture
{

            public final boolean cancel(boolean flag)
            {
                boolean flag1;
/* 616*/        if(flag1 = super.(flag))
/* 619*/            scheduledDelegate.cancel(flag);
/* 623*/        return flag1;
            }

            public final long getDelay(TimeUnit timeunit)
            {
/* 628*/        return scheduledDelegate.getDelay(timeunit);
            }

            public final int compareTo(Delayed delayed)
            {
/* 633*/        return scheduledDelegate.compareTo(delayed);
            }

            public final volatile int compareTo(Object obj)
            {
/* 601*/        return compareTo((Delayed)obj);
            }

            private final ScheduledFuture scheduledDelegate;

            public (ListenableFuture listenablefuture, ScheduledFuture scheduledfuture)
            {
/* 610*/        super(listenablefuture);
/* 611*/        scheduledDelegate = scheduledfuture;
            }
}
