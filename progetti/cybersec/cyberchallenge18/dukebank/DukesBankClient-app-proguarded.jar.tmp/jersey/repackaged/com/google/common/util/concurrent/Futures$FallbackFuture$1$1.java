// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            FutureCallback, Futures, ListenableFuture, FutureFallback, 
//            MoreExecutors

class this._cls1
    implements FutureCallback
{

            public void onSuccess(Object obj)
            {
/* 479*/        _mth1(obj);
            }

            public void onFailure(Throwable throwable)
            {
/* 484*/        if(ss._mth100(_fld0).isCancelled())
                {
/* 485*/            el(false);
/* 485*/            return;
                } else
                {
/* 487*/            xception(throwable);
/* 489*/            return;
                }
            }

            final xception this$1;

            l.fallback()
            {
/* 476*/        this$1 = this._cls1.this;
/* 476*/        super();
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/util/concurrent/Futures$FallbackFuture$1

/* anonymous class */
    class Futures.FallbackFuture._cls1
        implements FutureCallback
    {

                public void onSuccess(Object obj)
                {
/* 462*/            set(obj);
                }

                public void onFailure(Throwable throwable)
                {
/* 467*/            if(isCancelled())
/* 468*/                return;
/* 471*/            Futures.FallbackFuture.access$102(Futures.FallbackFuture.this, fallback.create(throwable));
/* 472*/            if(isCancelled())
                    {
/* 473*/                Futures.FallbackFuture.access$100(Futures.FallbackFuture.this).cancel(wasInterrupted());
/* 474*/                return;
                    }
/* 476*/            try
                    {
/* 476*/                Futures.addCallback(Futures.FallbackFuture.access$100(Futures.FallbackFuture.this), new Futures.FallbackFuture._cls1._cls1(), MoreExecutors.directExecutor());
/* 493*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 491*/            catch(Throwable throwable)
                    {
/* 492*/                setException(throwable);
                    }
/* 494*/            return;
                }

                final FutureFallback val$fallback;
                final Futures.FallbackFuture this$0;

                    
                    {
/* 459*/                this$0 = final_fallbackfuture;
/* 459*/                fallback = FutureFallback.this;
/* 459*/                super();
                    }
    }

}
