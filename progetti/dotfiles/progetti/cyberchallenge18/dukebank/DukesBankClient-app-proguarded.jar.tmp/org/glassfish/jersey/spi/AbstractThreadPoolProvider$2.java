// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractThreadPoolProvider.java

package org.glassfish.jersey.spi;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// Referenced classes of package org.glassfish.jersey.spi:
//            AbstractThreadPoolProvider

class this._cls0
    implements RejectedExecutionHandler
{

            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadpoolexecutor)
            {
            }

            final AbstractThreadPoolProvider this$0;

            ()
            {
/* 214*/        this$0 = AbstractThreadPoolProvider.this;
/* 214*/        super();
            }
}
