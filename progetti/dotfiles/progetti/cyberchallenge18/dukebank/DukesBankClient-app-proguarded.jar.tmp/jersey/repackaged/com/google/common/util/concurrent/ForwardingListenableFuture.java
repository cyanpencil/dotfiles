// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingListenableFuture.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ForwardingFuture, ListenableFuture

public abstract class ForwardingListenableFuture extends ForwardingFuture
    implements ListenableFuture
{
    public static abstract class SimpleForwardingListenableFuture extends ForwardingListenableFuture
    {

                protected final ListenableFuture _mthdelegate()
                {
/*  71*/            return _flddelegate;
                }

                protected volatile Future _mthdelegate()
                {
/*  61*/            return _mthdelegate();
                }

                protected volatile Object _mthdelegate()
                {
/*  61*/            return _mthdelegate();
                }

                private final ListenableFuture _flddelegate;

                protected SimpleForwardingListenableFuture(ListenableFuture listenablefuture)
                {
/*  66*/            _flddelegate = (ListenableFuture)Preconditions.checkNotNull(listenablefuture);
                }
    }


            protected ForwardingListenableFuture()
            {
            }

            protected abstract ListenableFuture _mthdelegate();

            public void addListener(Runnable runnable, Executor executor)
            {
/*  47*/        _mthdelegate().addListener(runnable, executor);
            }

            protected volatile Future _mthdelegate()
            {
/*  36*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  36*/        return _mthdelegate();
            }
}
