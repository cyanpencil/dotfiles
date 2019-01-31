// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutorServiceProvider.java

package org.glassfish.jersey.spi;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceProvider
{

    public abstract ExecutorService getExecutorService();

    public abstract void dispose(ExecutorService executorservice);
}
