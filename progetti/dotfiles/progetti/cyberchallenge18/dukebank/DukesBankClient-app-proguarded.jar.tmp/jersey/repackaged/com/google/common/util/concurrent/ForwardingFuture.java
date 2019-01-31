// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingFuture.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;
import jersey.repackaged.com.google.common.collect.ForwardingObject;

public abstract class ForwardingFuture extends ForwardingObject
    implements Future
{

            protected ForwardingFuture()
            {
            }

            protected abstract Future _mthdelegate();

            public boolean cancel(boolean flag)
            {
/*  48*/        return _mthdelegate().cancel(flag);
            }

            public boolean isCancelled()
            {
/*  53*/        return _mthdelegate().isCancelled();
            }

            public boolean isDone()
            {
/*  58*/        return _mthdelegate().isDone();
            }

            public Object get()
                throws InterruptedException, ExecutionException
            {
/*  63*/        return _mthdelegate().get();
            }

            public Object get(long l, TimeUnit timeunit)
                throws InterruptedException, ExecutionException, TimeoutException
            {
/*  69*/        return _mthdelegate().get(l, timeunit);
            }

            protected volatile Object _mthdelegate()
            {
/*  38*/        return _mthdelegate();
            }
}
