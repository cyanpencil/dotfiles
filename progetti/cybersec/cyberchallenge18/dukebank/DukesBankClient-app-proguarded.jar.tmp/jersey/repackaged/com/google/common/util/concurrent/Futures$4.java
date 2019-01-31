// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AsyncFunction, Futures, ListenableFuture

static class Future
    implements AsyncFunction
{

            public final ListenableFuture apply(ListenableFuture listenablefuture)
            {
/* 982*/        return listenablefuture;
            }

            public final volatile ListenableFuture apply(Object obj)
                throws Exception
            {
/* 980*/        return apply((ListenableFuture)obj);
            }

            Future()
            {
            }
}
