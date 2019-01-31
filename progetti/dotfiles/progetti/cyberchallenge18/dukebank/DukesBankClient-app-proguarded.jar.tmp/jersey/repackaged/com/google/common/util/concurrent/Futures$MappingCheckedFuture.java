// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractCheckedFuture, Futures, ListenableFuture

static class mapper extends AbstractCheckedFuture
{

            final Function mapper;

            (ListenableFuture listenablefuture, Function function)
            {
/*1802*/        super(listenablefuture);
/*1804*/        mapper = (Function)Preconditions.checkNotNull(function);
            }
}
