// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultClientAsyncExecutorProvider.java

package org.glassfish.jersey.client;

import java.util.logging.Logger;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.collection.Value;

// Referenced classes of package org.glassfish.jersey.client:
//            DefaultClientAsyncExecutorProvider

class val.poolSize
    implements Value
{

            public Integer get()
            {
/*  79*/        if(val$poolSize <= 0)
                {
/*  80*/            DefaultClientAsyncExecutorProvider.access$000().config(LocalizationMessages.IGNORED_ASYNC_THREADPOOL_SIZE(Integer.valueOf(val$poolSize)));
/*  82*/            return Integer.valueOf(0x7fffffff);
                } else
                {
/*  84*/            DefaultClientAsyncExecutorProvider.access$000().config(LocalizationMessages.USING_FIXED_ASYNC_THREADPOOL(Integer.valueOf(val$poolSize)));
/*  85*/            return Integer.valueOf(val$poolSize);
                }
            }

            public volatile Object get()
            {
/*  76*/        return get();
            }

            final int val$poolSize;
            final DefaultClientAsyncExecutorProvider this$0;

            ()
            {
/*  76*/        this$0 = final_defaultclientasyncexecutorprovider;
/*  76*/        val$poolSize = I.this;
/*  76*/        super();
            }
}
