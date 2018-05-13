// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateController.java

package org.glassfish.hk2.api;

import java.util.concurrent.Executor;

public interface ImmediateController
{
    public static final class ImmediateServiceState extends Enum
    {

                public static ImmediateServiceState[] values()
                {
/* 111*/            return (ImmediateServiceState[])$VALUES.clone();
                }

                public static ImmediateServiceState valueOf(String s)
                {
/* 111*/            return (ImmediateServiceState)Enum.valueOf(org/glassfish/hk2/api/ImmediateController$ImmediateServiceState, s);
                }

                public static final ImmediateServiceState SUSPENDED;
                public static final ImmediateServiceState RUNNING;
                private static final ImmediateServiceState $VALUES[];

                static 
                {
/* 117*/            SUSPENDED = new ImmediateServiceState("SUSPENDED", 0);
/* 122*/            RUNNING = new ImmediateServiceState("RUNNING", 1);
/* 111*/            $VALUES = (new ImmediateServiceState[] {
/* 111*/                SUSPENDED, RUNNING
                    });
                }

                private ImmediateServiceState(String s, int i)
                {
/* 111*/            super(s, i);
                }
    }


    public abstract Executor getExecutor();

    public abstract void setExecutor(Executor executor)
        throws IllegalStateException;

    public abstract long getThreadInactivityTimeout();

    public abstract void setThreadInactivityTimeout(long l)
        throws IllegalArgumentException;

    public abstract ImmediateServiceState getImmediateState();

    public abstract void setImmediateState(ImmediateServiceState immediateservicestate);
}
