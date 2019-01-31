// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorState.java

package org.glassfish.hk2.api;


public final class ServiceLocatorState extends Enum
{

            public static ServiceLocatorState[] values()
            {
/*  49*/        return (ServiceLocatorState[])$VALUES.clone();
            }

            public static ServiceLocatorState valueOf(String s)
            {
/*  49*/        return (ServiceLocatorState)Enum.valueOf(org/glassfish/hk2/api/ServiceLocatorState, s);
            }

            private ServiceLocatorState(String s, int i)
            {
/*  49*/        super(s, i);
            }

            public static final ServiceLocatorState RUNNING;
            public static final ServiceLocatorState SHUTDOWN;
            private static final ServiceLocatorState $VALUES[];

            static 
            {
/*  54*/        RUNNING = new ServiceLocatorState("RUNNING", 0);
/*  60*/        SHUTDOWN = new ServiceLocatorState("SHUTDOWN", 1);
/*  49*/        $VALUES = (new ServiceLocatorState[] {
/*  49*/            RUNNING, SHUTDOWN
                });
            }
}
