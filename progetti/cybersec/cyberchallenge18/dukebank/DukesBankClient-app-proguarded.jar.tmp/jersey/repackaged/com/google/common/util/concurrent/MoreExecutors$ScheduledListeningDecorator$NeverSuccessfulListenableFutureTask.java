// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Throwables;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, MoreExecutors

static final class delegate extends AbstractFuture
    implements Runnable
{

            public final void run()
            {
/* 648*/        try
                {
/* 648*/            _flddelegate.run();
/* 652*/            return;
                }
/* 649*/        catch(Throwable throwable)
                {
/* 650*/            setException(throwable);
/* 651*/            throw Throwables.propagate(throwable);
                }
            }

            private final Runnable _flddelegate;

            public (Runnable runnable)
            {
/* 643*/        _flddelegate = (Runnable)Preconditions.checkNotNull(runnable);
            }
}
