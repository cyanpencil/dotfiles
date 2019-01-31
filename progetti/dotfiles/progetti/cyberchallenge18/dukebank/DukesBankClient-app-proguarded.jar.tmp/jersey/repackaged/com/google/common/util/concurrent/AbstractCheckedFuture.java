// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractCheckedFuture.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            CheckedFuture, ForwardingListenableFuture, ListenableFuture

public abstract class AbstractCheckedFuture extends ForwardingListenableFuture.SimpleForwardingListenableFuture
    implements CheckedFuture
{

            protected AbstractCheckedFuture(ListenableFuture listenablefuture)
            {
/*  41*/        super(listenablefuture);
            }
}
