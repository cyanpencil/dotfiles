// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Errors.java

package org.glassfish.jersey.internal;

import org.glassfish.jersey.internal.util.Producer;

// Referenced classes of package org.glassfish.jersey.internal:
//            Errors

static class val.task
    implements Producer
{

            public final Void call()
            {
/* 289*/        val$task.run();
/* 290*/        return null;
            }

            public final volatile Object call()
            {
/* 286*/        return call();
            }

            final Runnable val$task;

            ucer(Runnable runnable)
            {
/* 286*/        val$task = runnable;
/* 286*/        super();
            }
}
