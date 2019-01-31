// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, Futures, MoreExecutors, ListenableFuture, 
//            FutureCallback

static class setException extends AbstractFuture
{

            _cls1.val.delegate(final ListenableFuture delegate)
            {
/*1100*/        Preconditions.checkNotNull(delegate);
/*1101*/        Futures.addCallback(delegate, new FutureCallback() {

                    public void onSuccess(Object obj)
                    {
/*1104*/                set(obj);
                    }

                    public void onFailure(Throwable throwable)
                    {
/*1109*/                if(delegate.isCancelled())
                        {
/*1110*/                    cancel(false);
/*1110*/                    return;
                        } else
                        {
/*1112*/                    setException(throwable);
/*1114*/                    return;
                        }
                    }

                    final ListenableFuture val$delegate;
                    final Futures.NonCancellationPropagatingFuture this$0;

                    
                    {
/*1101*/                this$0 = Futures.NonCancellationPropagatingFuture.this;
/*1101*/                delegate = listenablefuture;
/*1101*/                super();
                    }
        }, MoreExecutors.directExecutor());
            }
}
