// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AsyncFunction, Futures, ListenableFuture

static class val.function
    implements AsyncFunction
{

            public final ListenableFuture apply(Object obj)
            {
/* 760*/        return Futures.immediateFuture(obj = val$function.apply(obj));
            }

            final Function val$function;

            Future(Function function1)
            {
/* 758*/        val$function = function1;
/* 758*/        super();
            }
}
