// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScheduledExecutorServiceProvider.java

package org.glassfish.jersey.spi;

import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package org.glassfish.jersey.spi:
//            ExecutorServiceProvider

public interface ScheduledExecutorServiceProvider
    extends ExecutorServiceProvider
{

    public abstract ScheduledExecutorService getExecutorService();
}
