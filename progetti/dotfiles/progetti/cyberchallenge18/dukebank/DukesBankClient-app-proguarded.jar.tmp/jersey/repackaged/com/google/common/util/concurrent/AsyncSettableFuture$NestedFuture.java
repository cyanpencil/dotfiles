// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AsyncSettableFuture.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, AsyncSettableFuture, ListenableFuture

static final class <init> extends AbstractFuture
{

            final boolean setFuture(ListenableFuture listenablefuture)
            {
/*  93*/        boolean flag = set(listenablefuture);
/*  94*/        if(isCancelled())
/*  95*/            listenablefuture.cancel(wasInterrupted());
/*  97*/        return flag;
            }

            private ()
            {
            }

            ( )
            {
/*  91*/        this();
            }
}
