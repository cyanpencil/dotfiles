// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingListenableFuture.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Future;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ForwardingListenableFuture, ListenableFuture

public static abstract class delegate extends ForwardingListenableFuture
{

            protected final ListenableFuture _mthdelegate()
            {
/*  71*/        return _flddelegate;
            }

            protected volatile Future _mthdelegate()
            {
/*  61*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  61*/        return _mthdelegate();
            }

            private final ListenableFuture _flddelegate;

            protected (ListenableFuture listenablefuture)
            {
/*  66*/        _flddelegate = (ListenableFuture)Preconditions.checkNotNull(listenablefuture);
            }
}
