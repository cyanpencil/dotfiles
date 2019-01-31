// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransferQueue.java

package org.glassfish.jersey.internal.util.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public interface TransferQueue
    extends BlockingQueue
{

    public abstract boolean tryTransfer(Object obj);

    public abstract void transfer(Object obj)
        throws InterruptedException;

    public abstract boolean tryTransfer(Object obj, long l, TimeUnit timeunit)
        throws InterruptedException;

    public abstract boolean hasWaitingConsumer();

    public abstract int getWaitingConsumerCount();
}
