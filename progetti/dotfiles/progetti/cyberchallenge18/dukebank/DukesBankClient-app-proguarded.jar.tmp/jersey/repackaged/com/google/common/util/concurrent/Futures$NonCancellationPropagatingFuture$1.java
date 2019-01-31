// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            FutureCallback, Futures, ListenableFuture

class val.delegate
    implements FutureCallback
{

            public void onSuccess(Object obj)
            {
/*1104*/        t(obj);
            }

            public void onFailure(Throwable throwable)
            {
/*1109*/        if(val$delegate.isCancelled())
                {
/*1110*/            ncel(false);
/*1110*/            return;
                } else
                {
/*1112*/            tException(throwable);
/*1114*/            return;
                }
            }

            final ListenableFuture val$delegate;
            final tException this$0;

            ()
            {
/*1101*/        this$0 = final_;
/*1101*/        val$delegate = ListenableFuture.this;
/*1101*/        super();
            }
}
