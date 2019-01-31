// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractListeningExecutorService, MoreExecutors

static class delegate extends AbstractListeningExecutorService
{

            public boolean awaitTermination(long l, TimeUnit timeunit)
                throws InterruptedException
            {
/* 525*/        return _flddelegate.awaitTermination(l, timeunit);
            }

            public boolean isShutdown()
            {
/* 530*/        return _flddelegate.isShutdown();
            }

            public boolean isTerminated()
            {
/* 535*/        return _flddelegate.isTerminated();
            }

            public void shutdown()
            {
/* 540*/        _flddelegate.shutdown();
            }

            public List shutdownNow()
            {
/* 545*/        return _flddelegate.shutdownNow();
            }

            public void execute(Runnable runnable)
            {
/* 550*/        _flddelegate.execute(runnable);
            }

            private final ExecutorService _flddelegate;

            (ExecutorService executorservice)
            {
/* 519*/        _flddelegate = (ExecutorService)Preconditions.checkNotNull(executorservice);
            }
}
