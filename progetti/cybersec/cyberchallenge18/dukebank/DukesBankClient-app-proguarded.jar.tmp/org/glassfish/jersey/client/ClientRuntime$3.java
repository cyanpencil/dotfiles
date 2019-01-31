// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientRuntime.java

package org.glassfish.jersey.client;

import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRuntime

class val.task
    implements Runnable
{

            public void run()
            {
/* 210*/        ClientRuntime.access$500(ClientRuntime.this).runInScope(val$task);
            }

            final Runnable val$task;
            final ClientRuntime this$0;

            tScope()
            {
/* 207*/        this$0 = final_clientruntime;
/* 207*/        val$task = Runnable.this;
/* 207*/        super();
            }
}
