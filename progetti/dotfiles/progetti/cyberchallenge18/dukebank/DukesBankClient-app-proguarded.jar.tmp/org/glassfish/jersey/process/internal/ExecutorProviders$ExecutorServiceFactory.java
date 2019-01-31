// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutorProviders.java

package org.glassfish.jersey.process.internal;

import java.util.concurrent.ExecutorService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.spi.ExecutorServiceProvider;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            ExecutorProviders

static class executorProvider
    implements Factory
{

            public ExecutorService provide()
            {
/* 236*/        return executorProvider.getExecutorService();
            }

            public void dispose(ExecutorService executorservice)
            {
/* 241*/        executorProvider.dispose(executorservice);
            }

            public volatile void dispose(Object obj)
            {
/* 226*/        dispose((ExecutorService)obj);
            }

            public volatile Object provide()
            {
/* 226*/        return provide();
            }

            private final ExecutorServiceProvider executorProvider;

            private (ExecutorServiceProvider executorserviceprovider)
            {
/* 231*/        executorProvider = executorserviceprovider;
            }

}
