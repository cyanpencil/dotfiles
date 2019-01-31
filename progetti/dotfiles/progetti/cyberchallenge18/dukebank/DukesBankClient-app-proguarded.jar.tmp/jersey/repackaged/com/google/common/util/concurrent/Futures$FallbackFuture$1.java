// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            FutureCallback, FutureFallback, Futures, ListenableFuture, 
//            MoreExecutors

class val.fallback
    implements FutureCallback
{

            public void onSuccess(Object obj)
            {
/* 462*/        t(obj);
            }

            public void onFailure(Throwable throwable)
            {
/* 467*/        if(Cancelled())
/* 468*/            return;
/* 471*/        cess._mth102(this._cls0.this, val$fallback.create(throwable));
/* 472*/        if(Cancelled())
                {
/* 473*/            cess._mth100(this._cls0.this).cancel(sInterrupted());
/* 474*/            return;
                }
/* 476*/        try
                {
/* 476*/            Futures.addCallback(cess._mth100(this._cls0.this), new FutureCallback() {

                        public void onSuccess(Object obj)
                        {
/* 479*/                    set(obj);
                        }

                        public void onFailure(Throwable throwable1)
                        {
/* 484*/                    if(Futures.FallbackFuture.access$100(this$0).isCancelled())
                            {
/* 485*/                        cancel(false);
/* 485*/                        return;
                            } else
                            {
/* 487*/                        setException(throwable1);
/* 489*/                        return;
                            }
                        }

                        final Futures.FallbackFuture._cls1 this$1;

                    
                    {
/* 476*/                this$1 = Futures.FallbackFuture._cls1.this;
/* 476*/                super();
                    }
            }, MoreExecutors.directExecutor());
/* 493*/            return;
                }
                // Misplaced declaration of an exception variable
/* 491*/        catch(Throwable throwable)
                {
/* 492*/            tException(throwable);
                }
/* 494*/        return;
            }

            final FutureFallback val$fallback;
            final tException this$0;

            _cls1.this._cls1()
            {
/* 459*/        this$0 = final__pcls1;
/* 459*/        val$fallback = FutureFallback.this;
/* 459*/        super();
            }
}
