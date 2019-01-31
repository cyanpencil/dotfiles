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
/* 271*/        val$task.run();
/* 272*/        return null;
            }

            public final volatile Object call()
            {
/* 267*/        return call();
            }

            final Runnable val$task;

            ucer(Runnable runnable)
            {
/* 267*/        val$task = runnable;
/* 267*/        super();
            }
}
