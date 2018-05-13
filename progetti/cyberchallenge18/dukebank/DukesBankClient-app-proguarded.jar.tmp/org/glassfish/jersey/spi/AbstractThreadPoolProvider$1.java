// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractThreadPoolProvider.java

package org.glassfish.jersey.spi;

import java.util.concurrent.ThreadPoolExecutor;
import org.glassfish.jersey.internal.util.collection.Value;

// Referenced classes of package org.glassfish.jersey.spi:
//            AbstractThreadPoolProvider

class this._cls0
    implements Value
{

            public ThreadPoolExecutor get()
            {
/* 102*/        return createExecutor(getCorePoolSize(), AbstractThreadPoolProvider.access$000(AbstractThreadPoolProvider.this), getRejectedExecutionHandler());
            }

            public volatile Object get()
            {
/*  98*/        return get();
            }

            final AbstractThreadPoolProvider this$0;

            ()
            {
/*  98*/        this$0 = AbstractThreadPoolProvider.this;
/*  98*/        super();
            }
}
