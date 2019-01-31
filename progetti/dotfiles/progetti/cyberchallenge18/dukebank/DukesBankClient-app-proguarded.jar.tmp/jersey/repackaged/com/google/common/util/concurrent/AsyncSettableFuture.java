// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AsyncSettableFuture.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Future;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ForwardingListenableFuture, Futures, ListenableFuture, AbstractFuture

final class AsyncSettableFuture extends ForwardingListenableFuture
{
    static final class NestedFuture extends AbstractFuture
    {

                final boolean setFuture(ListenableFuture listenablefuture)
                {
/*  93*/            boolean flag = set(listenablefuture);
/*  94*/            if(isCancelled())
/*  95*/                listenablefuture.cancel(wasInterrupted());
/*  97*/            return flag;
                }

                private NestedFuture()
                {
                }

    }


            public static AsyncSettableFuture create()
            {
/*  41*/        return new AsyncSettableFuture();
            }

            private AsyncSettableFuture()
            {
/*  45*/        dereferenced = Futures.dereference(nested);
            }

            protected final ListenableFuture _mthdelegate()
            {
/*  50*/        return dereferenced;
            }

            public final boolean setFuture(ListenableFuture listenablefuture)
            {
/*  58*/        return nested.setFuture((ListenableFuture)Preconditions.checkNotNull(listenablefuture));
            }

            protected final volatile Future _mthdelegate()
            {
/*  37*/        return _mthdelegate();
            }

            protected final volatile Object _mthdelegate()
            {
/*  37*/        return _mthdelegate();
            }

            private final NestedFuture nested = new NestedFuture();
            private final ListenableFuture dereferenced;
}
