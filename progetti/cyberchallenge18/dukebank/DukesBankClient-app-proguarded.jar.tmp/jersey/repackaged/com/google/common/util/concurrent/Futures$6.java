// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            FutureCallback, Futures, Uninterruptibles, ListenableFuture

static class val.callback
    implements Runnable
{

            public final void run()
            {
                Object obj;
/*1308*/        try
                {
/*1308*/            obj = Uninterruptibles.getUninterruptibly(val$future);
                }
                // Misplaced declaration of an exception variable
/*1309*/        catch(Object obj)
                {
/*1310*/            val$callback.onFailure(((ExecutionException) (obj)).getCause());
/*1311*/            return;
                }
                // Misplaced declaration of an exception variable
/*1312*/        catch(Object obj)
                {
/*1313*/            val$callback.onFailure(((Throwable) (obj)));
/*1314*/            return;
                }
                // Misplaced declaration of an exception variable
/*1315*/        catch(Object obj)
                {
/*1316*/            val$callback.onFailure(((Throwable) (obj)));
/*1317*/            return;
                }
/*1319*/        val$callback.onSuccess(obj);
            }

            final ListenableFuture val$future;
            final FutureCallback val$callback;

            back(ListenableFuture listenablefuture, FutureCallback futurecallback)
            {
/*1301*/        val$future = listenablefuture;
/*1301*/        val$callback = futurecallback;
/*1301*/        super();
            }
}
