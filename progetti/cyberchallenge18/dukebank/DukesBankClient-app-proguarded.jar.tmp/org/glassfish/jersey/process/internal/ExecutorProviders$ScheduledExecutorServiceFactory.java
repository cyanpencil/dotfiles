// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutorProviders.java

package org.glassfish.jersey.process.internal;

import java.util.concurrent.ScheduledExecutorService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.spi.ScheduledExecutorServiceProvider;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            ExecutorProviders

static class executorProvider
    implements Factory
{

            public ScheduledExecutorService provide()
            {
/* 255*/        return executorProvider.getExecutorService();
            }

            public void dispose(ScheduledExecutorService scheduledexecutorservice)
            {
/* 260*/        executorProvider.dispose(scheduledexecutorservice);
            }

            public volatile void dispose(Object obj)
            {
/* 245*/        dispose((ScheduledExecutorService)obj);
            }

            public volatile Object provide()
            {
/* 245*/        return provide();
            }

            private final ScheduledExecutorServiceProvider executorProvider;

            private (ScheduledExecutorServiceProvider scheduledexecutorserviceprovider)
            {
/* 250*/        executorProvider = scheduledexecutorserviceprovider;
            }

}
