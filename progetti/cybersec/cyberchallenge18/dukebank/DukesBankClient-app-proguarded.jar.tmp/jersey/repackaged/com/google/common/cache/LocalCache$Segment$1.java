// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

class uture
    implements Runnable
{

            public void run()
            {
/*2331*/        try
                {
/*2331*/            tAndRecordStats(val$key, val$hash, val$loadingValueReference, val$loadingFuture);
/*2335*/            return;
                }
/*2332*/        catch(Throwable throwable)
                {
/*2333*/            LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", throwable);
/*2334*/            val$loadingValueReference.setException(throwable);
/*2336*/            return;
                }
            }

            final Object val$key;
            final int val$hash;
            final ueReference val$loadingValueReference;
            final ListenableFuture val$loadingFuture;
            final uture this$0;

            uture()
            {
/*2327*/        this$0 = final_uture;
/*2327*/        val$key = obj;
/*2327*/        val$hash = i;
/*2327*/        val$loadingValueReference = uereference;
/*2327*/        val$loadingFuture = ListenableFuture.this;
/*2327*/        super();
            }
}
