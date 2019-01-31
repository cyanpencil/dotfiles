// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executor;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, Futures, ListenableFuture, FutureFallback, 
//            FutureCallback, MoreExecutors

static class setException extends AbstractFuture
{

            public boolean cancel(boolean flag)
            {
/* 500*/        if(super.cancel(flag))
                {
/* 501*/            running.cancel(flag);
/* 502*/            return true;
                } else
                {
/* 504*/            return false;
                }
            }

            private volatile ListenableFuture running;



            _cls1.val.fallback(ListenableFuture listenablefuture, final FutureFallback fallback, Executor executor)
            {
/* 458*/        running = listenablefuture;
/* 459*/        Futures.addCallback(running, new FutureCallback() {

                    public void onSuccess(Object obj)
                    {
/* 462*/                set(obj);
                    }

                    public void onFailure(Throwable throwable)
                    {
/* 467*/                if(isCancelled())
/* 468*/                    return;
/* 471*/                running = fallback.create(throwable);
/* 472*/                if(isCancelled())
                        {
/* 473*/                    running.cancel(wasInterrupted());
/* 474*/                    return;
                        }
/* 476*/                try
                        {
/* 476*/                    Futures.addCallback(running, new FutureCallback() {

                                public void onSuccess(Object obj)
                                {
/* 479*/                            set(obj);
                                }

                                public void onFailure(Throwable throwable1)
                                {
/* 484*/                            if(running.isCancelled())
                                    {
/* 485*/                                cancel(false);
/* 485*/                                return;
                                    } else
                                    {
/* 487*/                                setException(throwable1);
/* 489*/                                return;
                                    }
                                }

                                final _cls1 this$1;

                            
                            {
/* 476*/                        this$1 = _cls1.this;
/* 476*/                        super();
                            }
                    }, MoreExecutors.directExecutor());
/* 493*/                    return;
                        }
                        // Misplaced declaration of an exception variable
/* 491*/                catch(Throwable throwable)
                        {
/* 492*/                    setException(throwable);
                        }
/* 494*/                return;
                    }

                    final FutureFallback val$fallback;
                    final Futures.FallbackFuture this$0;

                    
                    {
/* 459*/                this$0 = Futures.FallbackFuture.this;
/* 459*/                fallback = futurefallback;
/* 459*/                super();
                    }
        }, executor);
            }
}
